package com.testdemo.model;

import android.content.Context;

import com.testdemo.utils.PromptUtil;

/**
 * Created by XuYanping on 2017/3/3.
 */

public class BieKe implements Car {

    @Override
    public void driveWay(Context context) {
        PromptUtil.toastShowShort(context, "别克汽车-前驱");
    }

    @Override
    public void price(Context context) {
        PromptUtil.toastShowShort(context, "别克汽车-38万");
    }

    @Override
    public void carSeatNum(Context context) {
        PromptUtil.toastShowShort(context, "别克汽车-5座");
    }
}
