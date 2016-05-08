package com.example.huhu.adpter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huhu.R;
import com.example.huhu.bean.FirstPageInfo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
public class ListViewAdapter02 extends BaseAdapter {


    private Context mContext;

    private List<FirstPageInfo> mListData;

    private LayoutInflater inflater;

    public ListViewAdapter02(Context context,List<FirstPageInfo> list){
        mContext=context;
        mListData=list;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView = inflater.inflate(R.layout.item_listview,parent,false);
            holder=new ViewHolder();
            holder.name= (TextView) convertView.findViewById(R.id.item_list_txt_name);
            holder.intro= (TextView) convertView.findViewById(R.id.item_list_txt_intro);
            holder.picture= (SimpleDraweeView) convertView.findViewById(R.id.item_list_img);
            holder.price= (TextView) convertView.findViewById(R.id.item_list_txt_price);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.name.setText("商品"+position);
        holder.price.setText("￥ "+mListData.get(position).getPrice());
        Uri uri = Uri.parse("http://pic1.nipic.com/2008-09-08/200898163242920_2.jpg");
        holder.picture.setImageURI(uri);
        holder.intro.setText("123456578909876543421");

        return convertView;
    }

    public class ViewHolder{
        private TextView name;
        private TextView intro;
        private SimpleDraweeView picture;
        private TextView price;
    }
}
