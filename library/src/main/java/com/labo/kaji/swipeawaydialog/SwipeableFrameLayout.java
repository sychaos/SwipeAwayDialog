package com.labo.kaji.swipeawaydialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * @author kakajika
 * @since 15/08/15.
 */
public class SwipeableFrameLayout extends FrameLayout {

    private SwipeDismissTouchListener mTouchListener;

    public SwipeableFrameLayout(@NonNull Context context) {
        super(context);
    }

    public SwipeableFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSwipeDismissTouchListener(SwipeDismissTouchListener touchListener) {
        mTouchListener = touchListener;
        setOnTouchListener(touchListener);
        setClickable(true);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mTouchListener != null) {
            //当有mTouchListener时 直接拦截onTouch事件
            if (mTouchListener.onTouch(this, ev)) {
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

}
