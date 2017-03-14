package com.mvp.model;

import android.util.SparseArray;

import com.mvp.bean.UserBean;

/**
 * Created by XuYanping on 2017/2/22.
 */

public class UserModel implements IUserModel {

    private String mFristName;
    private String mLastName;
    private int mID;

    private SparseArray<UserBean> mSparseArray = new SparseArray<UserBean>();

    @Override
    public void setID(int id) {
        this.mID = id;
    }

    @Override
    public void setFirstName(String firstName) {
        this.mFristName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.mLastName = lastName;
        UserBean UserBean = new UserBean(mID, mFristName, mLastName);
        mSparseArray.append(mID, UserBean);
    }

    @Override
    public UserBean load(int id) {
        mID = id;
        UserBean userBean = mSparseArray.get(mID, new UserBean(0, "not found",
                "not found"));
        return userBean;
    }
}
