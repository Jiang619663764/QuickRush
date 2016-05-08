package com.example.huhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AddessActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtn_Add_Address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_addess);
        mBtn_Add_Address= (Button) this.findViewById(R.id.address_add_adr);
        mBtn_Add_Address.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_add_adr:
                Intent intent=new Intent(AddessActivity.this,AdrDetailActivity.class);
                startActivity(intent);
                break;
        }
    }
}
