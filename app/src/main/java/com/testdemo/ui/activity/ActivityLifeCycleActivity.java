package com.testdemo.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.testdemo.R;
import com.testdemo.ui.base.BaseActivity;

import butterknife.OnClick;

public class ActivityLifeCycleActivity extends BaseActivity {

    private static final String TAG = "ActivityLifeCycleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "this is onCreate()");
        addContentView(R.layout.activity_life_cycle);

    }

    @OnClick(R.id.bt_activity_life_cycle)
    public void onViewClicked() {
//        this.finish();
        startActivity(MainActivity.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("this is onStart()");
        Log.e(TAG, "this is onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("this is onRestart()");
        Log.e(TAG, "this is onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("this is onPause()");
        Log.e(TAG, "this is onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("this is onStop()");
        Log.e(TAG, "this is onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("this is onResume()");
        Log.e(TAG, "this is onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("this is onDestroy()");
        Log.e(TAG, "this is onDestroy()");
    }
}
