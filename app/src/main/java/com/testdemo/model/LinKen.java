package com.testdemo.model;

import android.content.Context;

import com.testdemo.utils.PromptUtil;

/**
 * Created by XuYanping on 2017/3/3.
 */

public class LinKen implements Car {


    @Override
    public void driveWay(Context context) {
        PromptUtil.toastShowShort(context, "林肯汽车-后驱");
    }

    @Override
    public void price(Context context) {
        PromptUtil.toastShowShort(context, "林肯汽车-1288万");
    }

    @Override
    public void carSeatNum(Context context) {
        PromptUtil.toastShowShort(context, "林肯汽车-12座");
    }
}
