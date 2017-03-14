package com.testdemo.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.testdemo.R;
import com.testdemo.model.AnQingProxy;
import com.testdemo.model.BenChi;
import com.testdemo.model.Car;
import com.testdemo.model.CarFactory;
import com.testdemo.model.HeFeiProxy;
import com.testdemo.model.Middleman;
import com.testdemo.model.MyObserver;
import com.testdemo.model.MyObserverble;
import com.testdemo.model.SpecificStrategy;
import com.testdemo.model.StrategyBox;
import com.testdemo.utils.PromptUtil;
import com.wevey.selector.dialog.DialogInterface;
import com.wevey.selector.dialog.MDSelectionDialog;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DesignModelActivity extends AppCompatActivity {

    private Context mContext;
    private ArrayList<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model);
        ButterKnife.bind(this);
        datas = new ArrayList<>();
        mContext = DesignModelActivity.this;
    }

    @OnClick({R.id.bt_observer_model, R.id.bt_strategy_model, R.id.bt_proxy_model, R.id.bt_factory_model})
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * 观察者模式
             */
            case R.id.bt_observer_model:
                MyObserverble observerble = new MyObserverble();
                MyObserver observerOne = new MyObserver("张真人", mContext);
                MyObserver observerTwo = new MyObserver("张三丰", mContext);
                MyObserver observerThree = new MyObserver("张无忌", mContext);
                observerble.addObserver(observerOne);
                observerble.addObserver(observerTwo);
                observerble.addObserver(observerThree);
                observerble.setInfomation(12, 25);
                break;
            /**
             * 策略模式
             */
            case R.id.bt_strategy_model:
                StrategyBox mStrategyBox = new StrategyBox(new SpecificStrategy(mContext));
                mStrategyBox.operate();
                break;
            /**
             * 代理模式
             */
            case R.id.bt_proxy_model:

                Middleman heFeiProxy = new Middleman(new HeFeiProxy(mContext));
                Middleman anQingProty = new Middleman(new AnQingProxy(mContext));
                heFeiProxy.sale();
                heFeiProxy.stock();
                anQingProty.sale();
                anQingProty.stock();

                break;

            /**
             * 工厂模式
             */
            case R.id.bt_factory_model:
                datas = new ArrayList<>();
                datas.add("生产奔驰汽车");
                datas.add("生产别克汽车");
                datas.add("生产林肯汽车");
                datas.add("随机生产汽车");
                new MDSelectionDialog.Builder(mContext).setCanceledOnTouchOutside(true).setItemTextColor(R.color.black_light).setItemHeight(50).setItemWidth(0.8f)  //屏幕宽度*0.8
                        .setItemTextSize(15).setCanceledOnTouchOutside(true).setOnItemListener(new DialogInterface.OnItemClickListener<MDSelectionDialog>() {
                    @Override
                    public void onItemClick(MDSelectionDialog dialog, View button, int position) {
//                        PromptUtil.toastShowShort(mContext, position + "");
                        productCar(position);
                        dialog.dismiss();
                    }
                }).build().setDatas(datas).show();
                break;

        }
    }

    private void productCar(int position) {

        switch (position) {

            case 0:
                //生产奔驰汽车
                Car benchi = CarFactory.productionCar(BenChi.class);
                benchi.driveWay(mContext);
                benchi.price(mContext);
                benchi.carSeatNum(mContext);
                break;
            case 1:
                //生产别克汽车
                Car bieke = CarFactory.productionCar(BenChi.class);
                bieke.driveWay(mContext);
                bieke.price(mContext);
                bieke.carSeatNum(mContext);
                break;
            case 2:
                //生产林肯汽车
                Car linken = CarFactory.productionCar(BenChi.class);
                linken.driveWay(mContext);
                linken.price(mContext);
                linken.carSeatNum(mContext);
                break;
        }
    }
}
