package com.testdemo.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;

import com.mvp.view.MVPActivity;
import com.mvvm.view.activity.DataBindingTestAty;
import com.testdemo.R;
import com.testdemo.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private List<String> mTab = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_main);
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        initView();
    }

    private void initView() {
        setTitleMethod("首页");
        mTab.add("如若");
        mTab.add("春风");
        mTab.add("知我意");
        setTab(mTab);
    }

    @OnClick({R.id.bt_design_model, R.id.bt_recyclerView, R.id.bt_custom_View, R.id.bt_mvp, R.id.bt_data_binding, R.id.bt_tab_layout, R.id.bt_activity_life_cycle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_design_model:
                startActivity(DesignModelActivity.class);
                break;
            case R.id.bt_recyclerView:
                startActivity(RecyclerViewTwoActivity.class);
                break;
            case R.id.bt_custom_View:
                startActivity(CustomViewActivity.class);
                break;
            case R.id.bt_mvp:
                startActivity(MVPActivity.class);
                break;
            case R.id.bt_data_binding:
                startActivity(DataBindingTestAty.class);
                break;
            case R.id.bt_tab_layout:
                startActivity(TabLayoutActivity.class);
                break;
            case R.id.bt_activity_life_cycle:
                startActivity(ActivityLifeCycleActivity.class);
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Notice");
            builder.setMessage("Are You Sure Exit ?");
            builder.setNegativeButton("No", null);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            builder.show();
        }
        return false;
    }
}
