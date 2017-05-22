package com.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.mvp.presenter.RegisterPresenter;
import com.testdemo.R;

import butterknife.BindView;
import cn.bmob.v3.Bmob;

/**
 * Created by XuYanping on 2017/3/29.
 */

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {
    @BindView(R.id.et_user_name)
    EditText mEtUserName;
    @BindView(R.id.et_psw)
    EditText mEtPsw;
    @BindView(R.id.et_sure_psw)
    EditText mEtSurePsw;
    @BindView(R.id.bt_register)
    Button mBtRegister;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected Toolbar getToolbar() {
        return null;
    }

    @Override
    public String getActivityTag() {
        return "RegisterActivity";
    }

    @Override
    public EditText getUserName() {
        return mEtUserName;
    }

    @Override
    public EditText getPsw() {
        return mEtPsw;
    }

    @Override
    public EditText getSurePsw() {
        return mEtSurePsw;
    }

    @Override
    public Button getRegister() {
        return mBtRegister;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.initView();
        Bmob.initialize(this, "daf0bb963d817c26304aa052fe70f92f");
    }
}
