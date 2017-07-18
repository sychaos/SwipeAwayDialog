package com.labo.kaji.swipeawaydialog.support.v4;

import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.labo.kaji.swipeawaydialog.SwipeDismissTouchListener;
import com.labo.kaji.swipeawaydialog.SwipeableFrameLayout;

/**
 * @author kakajika
 * @since 15/08/15.
 */
public class SwipeAwayDialogFragment extends DialogFragment {

    private boolean mSwipeable = true;
    private boolean mTiltEnabled = true;
    private boolean mSwipeLayoutGenerated = false;
    private SwipeDismissTouchListener mListener = null;
    private Window window;

    /**
     * Set whether dialog can be swiped away.
     */
    public void setSwipeable(boolean swipeable) {
        mSwipeable = swipeable;
    }

    /**
     * Get whether dialog can be swiped away.
     */
    public boolean isSwipeable() {
        return mSwipeable;
    }

    /**
     * Set whether tilt effect is enabled on swiping.
     */
    public void setTiltEnabled(boolean tiltEnabled) {
        mTiltEnabled = tiltEnabled;
        if (mListener != null) {
            mListener.setTiltEnabled(tiltEnabled);
        }
    }

    /**
     * Get whether tilt effect is enabled on swiping.
     */
    public boolean isTiltEnabled() {
        return mTiltEnabled;
    }

    /**
     * Called when dialog is swiped away to dismiss.
     * @return true to prevent dismissing
     */
    public boolean onSwipedAway(boolean toRight) {
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!mSwipeLayoutGenerated && getShowsDialog()) {
            final Window window = getDialog().getWindow();
            // 还没看懂 但是思路一定是 截获View中的content然后加在SwipeableFrameLayout里
            // SwipeableFrameLayout里添加SwipeableListener
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            View content = decorView.getChildAt(0);
            decorView.removeView(content);

            SwipeableFrameLayout layout = new SwipeableFrameLayout(getActivity());
            layout.addView(content);
            decorView.addView(layout);

            //  回调不解释
            mListener = new SwipeDismissTouchListener(decorView, new SwipeDismissTouchListener.SwipingCallbacks() {
                @Override
                public void onSwiping(float degree) {
                    WindowManager.LayoutParams windowParams = window.getAttributes();
                    windowParams.dimAmount = 0.8f * degree;
                    window.setAttributes(windowParams);
                }
            }, new SwipeDismissTouchListener.DismissCallbacks() {
                @Override
                public boolean canDismiss() {
                    return isCancelable() && mSwipeable;
                }

                @Override
                public void onDismiss(View view, boolean toRight) {
                    if (!onSwipedAway(toRight)) {
                        dismiss();
                    }
                }
            });

            //  mTiltEnabled设置是否旋转
            mListener.setTiltEnabled(mTiltEnabled);
            layout.setSwipeDismissTouchListener(mListener);
            layout.setOnTouchListener(mListener);
            layout.setClickable(true);
            mSwipeLayoutGenerated = true;
        }
    }

    public void onResume() {
        window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.8f;
        window.setAttributes(windowParams);
        super.onResume();
    }

}
