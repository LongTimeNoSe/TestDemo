package com.testdemo.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.testdemo.R;
import com.testdemo.utils.StatusBarUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    protected LayoutInflater mInflater;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.collapsingToolbarLayout)
    CoordinatorLayout mCollapsingToolbarLayout;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.base_activity_container)
    LinearLayout mBaseActivityContainer;
    DrawerLayout mBaseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflater = getLayoutInflater();

    }

    protected void addContentView(int layoutId) {

        setContentView(R.layout.activity_base);
        mBaseView = (DrawerLayout) findViewById(R.id.base_view);
//        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, mBaseView, getResources().getColor(R.color.colorPrimary));
        this.mBaseActivityContainer = (LinearLayout) findViewById(R.id.base_activity_container);
        ViewGroup viewContent = (ViewGroup) this.mInflater.inflate(layoutId, this.mBaseActivityContainer, false);
        mBaseActivityContainer.addView(viewContent);
        ButterKnife.bind(this, mBaseView);
        setSupportActionBar(mToolbar);

        //显示菜单图标
        /*
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mBaseView, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBaseView.setDrawerListener(toggle);
        toggle.syncState();
        */
    }

    public void hindToolBar() {
        mAppBar.setVisibility(View.GONE);
        mToolbar.setVisibility(View.GONE);
        mTabLayout.setVisibility(View.GONE);
    }

    public void setTitleMethod(String str) {
        setTitle(str);
    }

    public void setTab(List<String> tab) {

        for (int i = 0; i < tab.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tab.get(i)));
        }
    }

    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    public void startActivity(Class<?> cls, Bundle bundle) {

        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void showToast(String str) {
        Toast toast = new Toast(this);
        toast.setGravity(17, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(str);
        toast.show();
    }

    public void showToast(int text) {
        Toast toast = new Toast(this);
        toast.setGravity(17, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(text);
        toast.show();
    }
}
