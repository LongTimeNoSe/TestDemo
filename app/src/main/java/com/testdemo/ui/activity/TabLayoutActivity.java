package com.testdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.testdemo.R;
import com.testdemo.adapter.FragmentAdapter;
import com.testdemo.ui.base.BaseActivity;
import com.testdemo.ui.fragment.ShowFragment;
import com.testdemo.utils.PromptUtil;
import com.testdemo.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    List<Fragment> mList;
    @BindView(R.id.tb)
    TabLayout mTb;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.activity_tab_layout)
    DrawerLayout mActivityTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ButterKnife.bind(this);
        StatusBarUtil.setColorForDrawerLayout(this, mActivityTabLayout, getResources().getColor(R.color.colorPrimary));
//        StatusBarUtil.setTranslucentForDrawerLayout(this, mActivityTabLayout);
        setSupportActionBar(mToolbar);
        //设置标题  默认显示项目名称
        mCollapsingToolbarLayout.setTitle("首页");
        initTabView();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mActivityTabLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mActivityTabLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavView.setItemIconTintList(null);
        mNavView.setNavigationItemSelectedListener(this);

    }

    private void initTabView() {
        mList = new ArrayList<Fragment>();

        ShowFragment firstShowFragment = new ShowFragment();
        ShowFragment secondShowFragment = new ShowFragment();
        ShowFragment thirdShowFragment = new ShowFragment();

        Bundle firstBundle = new Bundle();
        Bundle secondBundle = new Bundle();
        Bundle thirdBundle = new Bundle();

        firstBundle.putString("type", "first");
        secondBundle.putString("type", "second");
        thirdBundle.putString("type", "third");

        firstShowFragment.setArguments(firstBundle);
        secondShowFragment.setArguments(secondBundle);
        thirdShowFragment.setArguments(thirdBundle);

        mList.add(firstShowFragment);
        mList.add(secondShowFragment);
        mList.add(thirdShowFragment);
        mVp.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mList, "main_view_pager"));
        mTb.setupWithViewPager(mVp);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_home:
                PromptUtil.snackbarShow(mActivityTabLayout, "首页");
                break;
            case R.id.nav_menu:
                PromptUtil.snackbarShow(mActivityTabLayout, "菜单");
                break;
            case R.id.nav_more:
                PromptUtil.snackbarShow(mActivityTabLayout, "更多");
                break;
            case R.id.nav_about:
                PromptUtil.snackbarShow(mActivityTabLayout, "关于");
                break;
            case R.id.nav_contact_me:
                PromptUtil.snackbarShow(mActivityTabLayout, "联系我");
                break;

        }

        mActivityTabLayout.closeDrawers();
        return true;
    }
}
