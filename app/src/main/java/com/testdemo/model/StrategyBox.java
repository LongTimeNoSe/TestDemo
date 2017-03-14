package com.testdemo.model;

/**
 * Created by XuYanping on 2017/3/3.
 */

/**
 * 锦囊（策略存放处）
 */
public class StrategyBox {

    private Strategy mStrategy;

    public StrategyBox(Strategy strategy) {
        mStrategy = strategy;
    }

    /**
     * 启用策略
     */
    public void operate() {
        mStrategy.operate();
    }
}
