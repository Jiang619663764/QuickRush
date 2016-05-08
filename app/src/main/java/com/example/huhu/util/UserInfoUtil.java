package com.example.huhu.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.huhu.RegisterActivity;
import com.example.huhu.bean.UserBaseInfo;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by Administrator on 2016/4/5.
 */
public class UserInfoUtil {

    public  boolean isUnExist = false;

    public  boolean isCouldLogin = false;

    /**
     * 判断用户登录名和密码是否正确
     *
     * @param context
     */
    public void isCanLogin(Context context, final String userName, final String psw) {
        BmobQuery<UserBaseInfo> userQuery = new BmobQuery<UserBaseInfo>();
        //根据姓名查询对应密码
        userQuery.addWhereEqualTo("name", userName);
        userQuery.findObjects(context, new FindListener<UserBaseInfo>() {
            @Override
            public void onSuccess(List<UserBaseInfo> list) {

                for (UserBaseInfo data : list) {
                    String passWord = data.getUserPsw();
                    if (psw.equals(passWord)) {
                        isCouldLogin = true;
                    } else {
                        isCouldLogin = false;
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                isCouldLogin = false;
            }
        });
    }

    /**
     * 判断用户是否存在
     *
     * @param context 上下文对象
     * @param s       用户名或手机号
     */
    public boolean isUserExist(final Context context, final String s) {

        BmobQuery<UserBaseInfo> userQuery = new BmobQuery<UserBaseInfo>();
        userQuery.findObjects(context, new FindListener<UserBaseInfo>() {
            @Override
            public void onSuccess(List<UserBaseInfo> list) {

                    for (UserBaseInfo data : list) {
                        String name = data.getUserName();
                        String phone = data.getUserPhone();
                        if (s.equals(name) || s.equals(phone)) {
                            Toast.makeText(context, "该用户已存在", Toast.LENGTH_SHORT).show();
                            isUnExist = false;
                        } else {
                            isUnExist = true;
                        }
                    }
            }

            @Override
            public void onError(int i, String s) {
                isUnExist = false;
            }
        });
        Log.e("UserInfo","isUnExist----------"+isUnExist);
        return isUnExist;
    }


    /**
     * 根据用户名或者手机号
     * 获取该用户的所有信息
     */
    public List<UserBaseInfo> getThisUserInfo(Context context, final String s, final String psw) {

        final List<UserBaseInfo> data = new ArrayList<UserBaseInfo>();
        BmobQuery<UserBaseInfo> userQuery = new BmobQuery<UserBaseInfo>();
        userQuery.findObjects(context, new FindListener<UserBaseInfo>() {
            @Override
            public void onSuccess(List<UserBaseInfo> userInfo) {
                for (UserBaseInfo list : userInfo) {
                    String name = list.getUserName();
                    String phone = list.getUserPhone();
                    String password = list.getUserPsw();
                    //判断用户名或手机号是否为本用户数据
                    if ((s.equals(name) || s.equals(phone)) && psw.equals(password)) {
                        UserBaseInfo info = new UserBaseInfo();
                        info.setUserName(list.getUserName());
                        info.setUserPhone(list.getUserPhone());
                        info.setIsVIP(list.isVIP());
                        info.setUserPsw(list.getUserPsw());
                        data.add(info);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        return data;
    }


}
