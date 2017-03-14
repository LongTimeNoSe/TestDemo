package com.testdemo.model;

import android.content.Context;

import com.testdemo.utils.PromptUtil;

/**
 * Created by XuYanping on 2017/3/3.
 */

/**
 * 合肥分代理
 */
public class HeFeiProxy implements GeneralProxy {

    private Context mContext;

    public HeFeiProxy(Context context) {
        mContext = context;
    }

    @Override
    public void stock() {
        PromptUtil.toastShowShort(mContext, "合肥代理人出货");
    }

    @Override
    public void sale() {
        PromptUtil.toastShowShort(mContext, "合肥代理人销售");
    }
}
