package com.testdemo.model;

/**
 * Created by XuYanping on 2017/3/3.
 */

/**
 * 中间代理人
 */
public class Middleman implements GeneralProxy {

    private GeneralProxy mGeneralProxy;

    public Middleman(GeneralProxy generalProxy) {
        this.mGeneralProxy = generalProxy;
    }

    @Override
    public void stock() {
        mGeneralProxy.stock();
    }

    @Override
    public void sale() {
        mGeneralProxy.sale();
    }
}
