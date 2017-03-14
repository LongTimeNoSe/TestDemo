package com.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.testdemo.R;
import com.testdemo.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MVPActivity extends BaseActivity {

    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.bt_weather)
    Button mBtWeather;
    @BindView(R.id.activity_mvp)
    LinearLayout mActivityMvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView(R.layout.activity_mvp);
        hindToolBar();
    }

    @OnClick({R.id.bt_login, R.id.bt_weather})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                startActivity(UserActivity.class);
                break;
            case R.id.bt_weather:
                break;
        }
    }
}
