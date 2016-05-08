package com.example.huhu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.huhu.adpter.UserInfoAdapter;

public class UserInfoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private UserInfoAdapter mUserInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_user_info);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mUserInfoAdapter=new UserInfoAdapter(this);
        mRecyclerView.setAdapter(mUserInfoAdapter);

        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
    }

}
