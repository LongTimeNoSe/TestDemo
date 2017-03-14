package com.testdemo.model;

import android.content.Context;

import com.testdemo.utils.PromptUtil;

/**
 * Created by XuYanping on 2017/3/3.
 */

public class BenChi implements Car {

    @Override
    public void driveWay(Context context) {

        PromptUtil.toastShowShort(context, "奔驰汽车-四驱");
    }

    @Override
    public void price(Context context) {
        PromptUtil.toastShowShort(context, "奔驰汽车-128万");
    }

    @Override
    public void carSeatNum(Context context) {
        PromptUtil.toastShowShort(context, "奔驰汽车-5座");
    }
}
