package com.testdemo.model;

/**
 * Created by XuYanping on 2017/3/3.
 */

public class CarFactory {

    public static Car productionCar(Class c) {

        Car car = null;

        try {
            car = (Car) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.out.println("必须指定汽车品牌");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("品牌错误");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("未找到相应品牌");
        }

        return car;
    }
}
