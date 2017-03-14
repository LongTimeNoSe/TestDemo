package com.mvp.presenter;

import com.mvp.bean.UserBean;
import com.mvp.model.IUserModel;
import com.mvp.model.UserModel;
import com.mvp.view.IUserView;

/**
 * Created by XuYanping on 2017/2/22.
 */

public class UserPresenter {

    private IUserModel mIUserModel;
    private IUserView mIUserView;

    public UserPresenter(IUserView IUserView) {
        mIUserView = IUserView;
        mIUserModel = new UserModel();
    }

    public void saveInfo(int id, String firstName, String lastName) {
        mIUserModel.setID(id);
        mIUserModel.setFirstName(firstName);
        mIUserModel.setLastName(lastName);
    }

    public void loadInfo(int id) {
        UserBean userBean = mIUserModel.load(id);
        mIUserView.setId(userBean.getId());
        mIUserView.setFirstName(userBean.getFirstName());
        mIUserView.setLastName(userBean.getLastName());
    }
}
