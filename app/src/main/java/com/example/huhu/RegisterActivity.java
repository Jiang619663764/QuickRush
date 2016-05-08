package com.example.huhu;

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
import com.example.huhu.util.UserInfoUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 注册的界面
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 注册用户名
     */
    private EditText mEdt_name_register;
    /**
     * 注册密码
     */
    private EditText mEdt_psw_register;
    /**
     * 重复密码
     */
    private EditText mEdt_psw_repeat;
    /**
     * 重复密码
     */
    private Button mBtn_submit_register;
    /**
     * 电话号码
     */
    private String phoneNum;
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String psw;
    /**
     * 注册得到的id
     */
    public static String ObjectID = null;

    private boolean isReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
        initView();
        Intent intent = getIntent();
        phoneNum = intent.getStringExtra("phone");
        mBtn_submit_register.setOnClickListener(this);

        BmobQuery<UserBaseInfo> userQuery = new BmobQuery<UserBaseInfo>();
        userQuery.findObjects(this, new FindListener<UserBaseInfo>() {
            @Override
            public void onSuccess(List<UserBaseInfo> list) {

                for (UserBaseInfo data : list) {
                    String name = data.getUserName();
                    String phone = data.getUserPhone();
                    if (phoneNum.equals(phone)) {
                        Toast.makeText(RegisterActivity.this, "该用户已存在", Toast.LENGTH_SHORT).show();
                        isReg = false;
                        break;
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    /**
     * 初始化控件
     */
    private void initView() {

        mEdt_name_register = (EditText) findViewById(R.id.edt_name_register);
        mEdt_psw_register = (EditText) findViewById(R.id.edt_psw_register);
        mEdt_psw_repeat = (EditText) findViewById(R.id.edt_psw_repeat_register);
        mBtn_submit_register = (Button) findViewById(R.id.btn_submit_register);
    }

    @Override
    public void onClick(View v) {
        name = mEdt_name_register.getText().toString();
        psw = mEdt_psw_register.getText().toString();
        String psw_rep = mEdt_psw_repeat.getText().toString();

            if (psw.equals(psw_rep)) {
                if(isReg==false){
                    final UserBaseInfo info = new UserBaseInfo();
                    info.setUserName(name);
                    info.setUserPsw(psw);
                    info.setUserPhone(phoneNum);
                    //保存数据到Bmob数据库
                    info.save(getApplicationContext());
                    Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }

}
