package com.labo.kaji.swipeawaydialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cloudist on 2017/7/18.
 */

public class ViewHelper {

    public static void initSwipelistener(View view, Context context, SwipeDismissTouchListener swipeDismissTouchListener) {
        ViewGroup decorView = (ViewGroup) view.getParent();
        decorView.removeView(view);

        SwipeableFrameLayout layout = new SwipeableFrameLayout(context);
        layout.addView(view);
        layout.setLayoutParams(decorView.getLayoutParams());
        decorView.addView(layout);

        //  mTiltEnabled设置是否旋转
        layout.setSwipeDismissTouchListener(swipeDismissTouchListener);
        layout.setOnTouchListener(swipeDismissTouchListener);
        layout.setClickable(true);
    }
}
