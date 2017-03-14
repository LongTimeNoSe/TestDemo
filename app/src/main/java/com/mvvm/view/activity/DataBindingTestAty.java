package com.mvvm.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.mvvm.model.User;
import com.testdemo.R;
import com.testdemo.databinding.ActivityDataBindingTestAtyBinding;
import com.testdemo.utils.PromptUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DataBindingTestAty extends AppCompatActivity {

    ActivityDataBindingTestAtyBinding mBinding;
    @BindView(R.id.activity_data_binding_text_aty)
    LinearLayout mActivityDataBindingTextAty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_test_aty);
//        mBinding = ActivityDataBindingTestAtyBinding.inflate(getLayoutInflater());
        ButterKnife.bind(this);
        User user = new User("小小徐", "小徐");
        mBinding.setUser(user);
        mBinding.setHandler(new MyHandler());
    }

    public class MyHandler {
        public void onButtonClick(View view) {
            PromptUtil.snackbarShowShort(mActivityDataBindingTextAty, "点击事件");
        }
    }
}
