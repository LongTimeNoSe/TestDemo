package com.testdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by XuYanping on 2017/3/10.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private String tag;
    private List<Fragment> mList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> mList, String tag) {
        super(fm);
        this.mList = mList;
        this.tag = tag;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {

        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (tag.equals("main_view_pager")) {

            switch (position) {
                case 0:
                    return "春风十里";
                case 1:
                    return "不如";
                case 2:
                    return "你";
            }
        }
        return null;
    }
}
