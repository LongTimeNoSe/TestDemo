package com.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.mvp.presenter.UserPresenter;
import com.testdemo.R;
import com.testdemo.utils.PromptUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends AppCompatActivity implements IUserView {

    @BindView(R.id.id_edt)
    EditText mIdEdt;
    @BindView(R.id.first_name_edt)
    EditText mFirstNameEdt;
    @BindView(R.id.last_name_edt)
    EditText mLastNameEdt;
    @BindView(R.id.activity_user)
    RelativeLayout mActivityUser;

    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        mUserPresenter = new UserPresenter(this);
    }

    @OnClick({R.id.saveButton, R.id.loadButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveButton:
                mUserPresenter.saveInfo(getId(), getFirstName(), getLastName());

                break;
            case R.id.loadButton:
                if (mIdEdt.getText().toString().trim().equals("") | mIdEdt.getText().toString().trim() == null) {
                    PromptUtil.snackbarShowShort(mActivityUser, "请先输入查询ID");
                    return;
                }
                mUserPresenter.loadInfo(getId());
                break;
        }
    }

    @Override
    public int getId() {
        return Integer.parseInt(mIdEdt.getText().toString().trim());
    }

    @Override
    public String getFirstName() {
        return mFirstNameEdt.getText().toString().trim();
    }

    @Override
    public String getLastName() {
        return mLastNameEdt.getText().toString().trim();
    }

    @Override
    public void setId(int id) {
        mIdEdt.setText(id + "");
    }

    @Override
    public void setFirstName(String firstName) {
        mFirstNameEdt.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        mLastNameEdt.setText(lastName);
    }
}
