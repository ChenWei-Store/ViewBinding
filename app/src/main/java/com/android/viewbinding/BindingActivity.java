package com.android.viewbinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.viewBinding.BindView;


public class BindingActivity extends AppCompatActivity {
    @BindView(viewId = R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);
//        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container, BindingFragment.newInstance(), null)
                .commit();

    }



}
