package com.mybatis.domain.user;

import java.io.Serializable;

/*
 * 用户model
 *
 * Created by yunkai on 2017/11/30.
 */
public class YunKaiUserModel implements Serializable{

    private Integer id;

    private String userName;  //用户名称

    private String userPhone;  //用户联系方式

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
