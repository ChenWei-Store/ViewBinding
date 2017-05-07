package com.android.viewbinding;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.viewBinding.BindView;
import com.viewBinding.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(viewId = R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewBinding.bind(this);
        setSupportActionBar(toolbar);
    }

    @OnClick(viewId = R.id.btn_binding_activity)
    void startToBindingActivity(){
        startToActivity(BindingActivity.class);
    }

    @OnClick(viewId = R.id.btn_list_activity)
    void startToListActivity(){
        startToActivity(ListActvity.class);
    }

    @OnClick(viewId = R.id.btn_no_binding_activity)
    void startToNoBindingActivity(){
        startToActivity(NoBindingActivity.class);
    }

    private void startToActivity(Class targetCls){
        Intent intent = new Intent(this, targetCls);
        startActivity(intent);
    }

}
