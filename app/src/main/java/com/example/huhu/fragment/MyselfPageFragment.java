package com.example.huhu.fragment;


import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huhu.AddessActivity;
import com.example.huhu.LoginActivity;
import com.example.huhu.R;
import com.example.huhu.bean.UserBaseInfo;

/**
 * 我的信息页的Fragment
 * A simple {@link Fragment} subclass.
 */
public class MyselfPageFragment extends Fragment implements View.OnClickListener{

    public static boolean isLogin=false;

    public static UserBaseInfo userInfo;
    /**
     * 登录按钮
     */
    private Button mButton_login;
    /**
     * 用户信息布局
     */
    private LinearLayout mLayout_user;
    /**
     * 用户头像
     */
    private ImageView mImage_user;
    /**
     * 用户名信息
     */
    private TextView mText_user;
    /**
     * 收货地址信息
     */
    private Button mBtn_Address;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    /**
     * 请求码
     */
    public static final int LOGIN_REQUEST_CODE=1;

    public MyselfPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_myself_page, container, false);
        initView(view);

        if(sharedPreferences.getBoolean("login",false)){
            mButton_login.setVisibility(View.GONE);
            mLayout_user.setVisibility(View.VISIBLE);
            mText_user.setText(userInfo.getUserName());
        }

        return view;
    }

    /**
     * 初始化控件
     * @param view
     */
    private void initView(View view) {

        mButton_login= (Button) view.findViewById(R.id.btn_login_myself_page);
        mLayout_user= (LinearLayout) view.findViewById(R.id.layout_user_myself_page);
        mImage_user= (ImageView) view.findViewById(R.id.img_user_myself_page);
        mText_user= (TextView) view.findViewById(R.id.txt_user_myself_page);

        mBtn_Address= (Button) view.findViewById(R.id.my_address);

        mButton_login.setOnClickListener(this);
        mBtn_Address.setOnClickListener(this);
    }

    private void saveLoginInfo(Context context,Boolean isLogin){
        sharedPreferences=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putBoolean("login",isLogin);
        editor.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login_myself_page:
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,LOGIN_REQUEST_CODE);
                break;
            case R.id.my_address:
                Intent intent1=new Intent(getActivity(), AddessActivity.class);
                startActivity(intent1);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==LOGIN_REQUEST_CODE&&resultCode==20){
            mButton_login.setVisibility(View.GONE);
            mLayout_user.setVisibility(View.VISIBLE);
            Bundle bundle=data.getBundleExtra("userInfo");
            userInfo= (UserBaseInfo) bundle.get("mUserInfo");
            mText_user.setText(userInfo.getUserName());
            isLogin=true;
            saveLoginInfo(getActivity(),isLogin);
        }
    }
}
