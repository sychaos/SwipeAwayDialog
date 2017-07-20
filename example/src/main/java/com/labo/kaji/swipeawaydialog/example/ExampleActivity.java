package com.labo.kaji.swipeawaydialog.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.labo.kaji.swipeawaydialog.SwipeableFrameLayout;

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
    SwipeableFrameLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.logo_cloudist)
    void onShowAppCompatAlertDialog() {
        DemoDialog.newInstance().show(getSupportFragmentManager(),"");
    }

}
