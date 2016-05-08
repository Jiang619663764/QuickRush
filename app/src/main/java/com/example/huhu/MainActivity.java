package com.example.huhu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;

import com.example.huhu.fragment.HomePageFragment;
import com.example.huhu.fragment.MyselfPageFragment;
import com.example.huhu.fragment.ShoppingPageFragment;
import com.example.huhu.fragment.VIPPageFragment;
import com.example.huhu.view.MyToolBar;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        save();
        initView();


        mRadioGroup.setOnCheckedChangeListener(this);
    }


    /**
     * 初始化控件
     */
    private void initView() {

        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.fl_fragment_main, new HomePageFragment());
        mFragmentTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (checkedId) {
            case R.id.rb_first_page_main:
                ft.replace(R.id.fl_fragment_main, new HomePageFragment());
                break;
            case R.id.rb_vip_page_main:
                ft.replace(R.id.fl_fragment_main, new VIPPageFragment());
                break;
            case R.id.rb_shopping_page_main:
                SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
                if (sharedPreferences.getBoolean("login", false)) {
                    ft.replace(R.id.fl_fragment_main, new ShoppingPageFragment());
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rb_myself_page_main:
                ft.replace(R.id.fl_fragment_main, new MyselfPageFragment());
                break;
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("确定要退出？");
        alertDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    private void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login", false);
        editor.commit();
    }
}
