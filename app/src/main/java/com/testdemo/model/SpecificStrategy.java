package com.testdemo.model;

/**
 * Created by XuYanping on 2017/3/3.
 */

import android.content.Context;

import com.testdemo.utils.PromptUtil;

/**
 * 具体的策略
 * 新增其他策略时需新增实现类实现策略接口
 */
public class SpecificStrategy implements Strategy {

    Context mContext;

    public SpecificStrategy(Context context) {
        this.mContext = context;
    }

    @Override
    public void operate() {
        PromptUtil.toastShowShort(mContext, "具体的策略");
    }
}
