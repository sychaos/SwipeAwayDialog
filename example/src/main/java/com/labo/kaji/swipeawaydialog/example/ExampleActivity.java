package com.labo.kaji.swipeawaydialog.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.labo.kaji.swipeawaydialog.SwipeDismissTouchListener;
import com.labo.kaji.swipeawaydialog.ViewHelper;
import com.labo.kaji.swipeawaydialog.example.ExampleDialogFragment.Type;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author kakajika
 * @since 15/08/15.
 */
public class ExampleActivity extends AppCompatActivity {

    @Bind(R.id.root)
    RelativeLayout root;
    @Bind(R.id.layout)
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        ButterKnife.bind(this);

        ViewHelper.initSwipelistener(layout, ExampleActivity.this,
                new SwipeDismissTouchListener(layout, new SwipeDismissTouchListener.SwipingCallbacks() {
                    @Override
                    public void onSwiping(float degree) {
                    }
                }, new SwipeDismissTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss() {
                        return true;
                    }

                    @Override
                    public void onDismiss(View view, boolean toRight) {
                    }
                }));

    }

    @OnClick(R.id.logo_cloudist)
    void onShowAppCompatAlertDialog() {
        ExampleDialogFragment.newInstance(Type.APPCOMPAT).show(getSupportFragmentManager(), "sddds");
    }
}
