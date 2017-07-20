package com.labo.kaji.swipeawaydialog.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.labo.kaji.swipeawaydialog.SwipeDismissTouchListener;
import com.labo.kaji.swipeawaydialog.support.v4.SwipeAwayDialogFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cloudist on 2017/2/8.
 */

public class DemoDialog extends SwipeAwayDialogFragment {

    @Bind(R.id.layout)
    FrameLayout layout;

    public static DemoDialog newInstance() {
        DemoDialog fragment = new DemoDialog();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.demo_dialog, container);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setCanceledOnTouchOutside(false);
    }

    @Override
    public SwipeDismissTouchListener initListener(View decorView) {
        return new SwipeDismissTouchListener(decorView, new SwipeDismissTouchListener.DismissCallbacks() {
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

            @Override
            public void onSwiping(float degree) {
                WindowManager.LayoutParams windowParams = window.getAttributes();
                windowParams.dimAmount = 0.8f * degree;
                window.setAttributes(windowParams);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
