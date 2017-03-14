package com.testdemo.ui.activity;

import android.os.Bundle;

import com.testdemo.R;
import com.testdemo.ui.base.BaseActivity;
import com.testdemo.ui.view.ClearView;
import com.testdemo.utils.MemoryManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by XuYanping on 2017/2/20.
 */

public class CustomViewActivity extends BaseActivity {

    @BindView(R.id.clear_view)
    ClearView mClearView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_custom_view);
        hindToolBar();
    }

    @OnClick(R.id.up_speed)
    public void onClick() {
        initAngle();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAngle();
    }

    private void initAngle() {

        long free = MemoryManager.getPhoneFreeRamMemory(this);
        long all = MemoryManager.getPhoneTotalRamMemory();
        int angle = (int) ((all - free) * 360 / all);
        mClearView.setAngleWithAnim(angle);

    }
}
