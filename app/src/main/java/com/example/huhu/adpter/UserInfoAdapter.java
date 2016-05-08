package com.example.huhu.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huhu.R;
import com.example.huhu.bean.UserBaseInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.MyViewHolder> {

    private Context mContext;

    private String title[]={"头像","昵称","电话号码","性别","我的收货地址"};

    private UserBaseInfo mContentData;

    private String content[]={"","","","",""};

    public UserInfoAdapter(Context context){
        mContext=context;
        mContentData=new UserBaseInfo();
        getData();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(mContext).inflate(R.layout.item_user_info,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTitle.setText(title[position]);
        holder.mContent.setText(content[position]);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mTitle;
        TextView mContent;
        public MyViewHolder(View itemView) {
            super(itemView);

            mTitle= (TextView) itemView.findViewById(R.id.user_info_title);
            mContent= (TextView) itemView.findViewById(R.id.user_info_content);
        }

    }

    private void getData(){
        content[0]="";
        content[1]=mContentData.getUserName();
        content[2]=mContentData.getUserPhone();
        content[3]="";
        content[4]="";
    }


}
