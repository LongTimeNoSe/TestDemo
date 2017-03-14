package com.testdemo.model;

import android.content.Context;

import com.testdemo.utils.PromptUtil;

/**
 * Created by XuYanping on 2017/3/3.
 */

/**
 * 安庆分代理
 */
public class AnQingProxy implements GeneralProxy {

    private Context mContext;

    public AnQingProxy(Context context) {
        mContext = context;
    }

    @Override
    public void stock() {
        PromptUtil.toastShowShort(mContext, "安庆代理人出货");
    }

    @Override
    public void sale() {
        PromptUtil.toastShowShort(mContext, "安庆代理人销售");
    }
}
