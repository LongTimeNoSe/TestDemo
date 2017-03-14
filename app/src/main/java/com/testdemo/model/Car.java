package com.testdemo.model;

import android.content.Context;

/**
 * Created by XuYanping on 2017/3/3.
 */

public interface Car {



    /**
     * 驱动方式
     */
    void driveWay(Context context);

    /**
     * 价格
     */
    void price(Context context);

    /**
     * 车座数
     */
    void carSeatNum(Context context);

}
