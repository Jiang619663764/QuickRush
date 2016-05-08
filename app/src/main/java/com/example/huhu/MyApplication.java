package com.example.huhu;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import cn.bmob.v3.Bmob;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2016/4/13.
 */
public class MyApplication extends Application {

    /**
     * Bmob的key
     */
    private static final String BOMB_APP_KEY = "3d8ef47ae18bc941f25568295e16057f";
    /**
     * 短信验证的Key
     */
    private static final String SMS_APP_KEY = "10a71b20e0ee8";
    /**
     * 短信验证的secret
     */
    private static final String SMS_APP_SECRET = "a8365658e019a4cbf9f90128937d23e2";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Fresco
        Fresco.initialize(this);
        //初始化BmobSDK
        Bmob.initialize(this, BOMB_APP_KEY);
        //初始化SmS短信验证SDK
        SMSSDK.initSDK(this, SMS_APP_KEY, SMS_APP_SECRET);


    }
}
