package com.testdemo.model;

import android.content.Context;
import android.util.Log;

import com.testdemo.utils.PromptUtil;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by XuYanping on 2017/2/7.
 */

/**
 * 观察者
 */
public class MyObserver implements Observer {

    private String name;
    private int edition;
    private float cost;
    private Context mContext;

    public MyObserver(String name, Context context) {
        this.name = name;
        mContext = context;
    }

    public void buy() {
        PromptUtil.toastShowShort(mContext, name + "购买了第" + edition + "期的杂志，花费了" + cost + "元。");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MyObserverble) {
            MyObserverble observerble = (MyObserverble) o;
            this.edition = observerble.getEdition();
            this.cost = observerble.getCost();
            buy();
        }

    }
}
