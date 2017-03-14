package com.mvp.model;

import com.mvp.bean.UserBean;

/**
 * Created by XuYanping on 2017/2/22.
 */

public interface IUserModel {


    void setID(int id);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    UserBean load(int id);

}
