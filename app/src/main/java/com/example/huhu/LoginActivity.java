package com.example.huhu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huhu.bean.UserBaseInfo;
import com.example.huhu.fragment.MyselfPageFragment;
import com.example.huhu.util.UserInfoUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    UserInfoUtil mUserInfoUtil;
    /**
     * 用户名
     */
    private EditText mEdt_name_user;
    /**
     * 用户密码
     */
    private EditText mEdt_psw_user;
    /**
     * 登录
     */
    private Button mBtn_login;
    /**
     * 注册
     */
    private Button mBtn_register;

    private UserInfoUtil mInfoUtil;

    private UserBaseInfo mUserInfo;

    private boolean isCanLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);
        initView();

    }

    /**
     * 初始化控件
     */
    private void initView() {

        mUserInfoUtil=new UserInfoUtil();

        mEdt_name_user = (EditText) findViewById(R.id.edt_name_user_login);
        mEdt_psw_user = (EditText) findViewById(R.id.edt_psw_user_login);
        mBtn_login = (Button) findViewById(R.id.btn_login);
        mBtn_register = (Button) findViewById(R.id.btn_register);
        mBtn_login.setOnClickListener(this);
        mBtn_register.setOnClickListener(this);
    }

    /**
     * 提交数据到SMSSDK中
     *
     * @param country 国家
     * @param phone   电话
     */
    public void submitUserInfo(String country, String phone) {
        Random r = new Random();
        String uid = Math.abs(r.nextInt()) + "";
        String nickName = "jp";
        SMSSDK.submitUserInfo(uid, nickName, null, country, phone);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login://登录操作
                final String name = mEdt_name_user.getText().toString();
                final String psw = mEdt_psw_user.getText().toString();

                // 用bmob查询登录名和密码是否正确
                BmobQuery<UserBaseInfo> query = new BmobQuery<UserBaseInfo>();
                query.findObjects(this, new FindListener<UserBaseInfo>() {

                    @Override
                    public void onError(int arg0, String arg1) {
                    }

                    @Override
                    public void onSuccess(List<UserBaseInfo> info) {

                        for (UserBaseInfo infos : info) {
                            String n = infos.getUserName();
                            String phone=infos.getUserPhone();
                            String p = infos.getUserPsw();
                            //判断用户名和密码是否匹配
                            if (n.equals(name)
                                    && p.equals(psw)
                                    || (phone.equals(name) && p
                                    .equals(psw))) {

                                mUserInfo = new UserBaseInfo();
                                mUserInfo.setUserName(name);
                                mUserInfo.setUserPhone(infos.getUserPhone());

                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("mUserInfo", (Serializable) mUserInfo);
                                intent.putExtra("userInfo", bundle);
                                setResult(20, intent);
                                finish();
                                return;
                            }
                        }
                        Toast.makeText(LoginActivity.this, "输入的用户名或密码不正确",
                                Toast.LENGTH_SHORT).show();

                    }
                });

                break;
            case R.id.btn_register:
                final RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    @Override
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            //获取数据
                            HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                            submitUserInfo(country, phone);
                            Intent intent = new Intent(LoginActivity.this,
                                    RegisterActivity.class);
                            intent.putExtra("phone", phone);
                            registerPage.startActivity(intent);
                        }
                    }
                });
                registerPage.show(this);
                break;
        }
    }
}
