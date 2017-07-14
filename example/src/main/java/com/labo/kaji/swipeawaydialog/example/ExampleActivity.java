package com.labo.kaji.swipeawaydialog.example;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.labo.kaji.swipeawaydialog.example.ExampleDialogFragment.Type;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author kakajika
 * @since 15/08/15.
 */
public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.logo_cloudist)
    void onShowAppCompatAlertDialog() {
        ExampleDialogFragment.newInstance(Type.APPCOMPAT).show(getSupportFragmentManager(),"sddds");
    }
}
