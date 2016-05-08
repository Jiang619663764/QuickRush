package com.example.huhu.bean;

import android.os.Parcelable;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 用户的基础信息
 * Created by Administrator on 2016/4/1.
 */
public class UserBaseInfo extends BmobObject implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 用户名字
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPsw;
    /**
     * 用户电话号码
     */
    private String userPhone;
    /**
     * 用户是否为VIP
     */
    private boolean isVIP;
    /**
     * 用户地址
     */
    private String userAddress;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setIsVIP(boolean isVIP) {
        this.isVIP = isVIP;
    }
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
