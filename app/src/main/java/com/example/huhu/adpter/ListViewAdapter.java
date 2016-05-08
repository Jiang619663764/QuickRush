package com.example.huhu.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huhu.R;
import com.example.huhu.bean.FirstPageInfo;

import java.util.List;


/**
 * Created by Administrator on 2016/3/31.
 */
public class ListViewAdapter extends BaseAdapter {

    private Context mContext;

    private List<FirstPageInfo> mListData;

    private LayoutInflater inflater;

    public ListViewAdapter(Context context, List<FirstPageInfo> list) {

        mContext = context;
        mListData = list;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 根据position判断类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return position > 0 ? 0 : 1;
    }

    /**
     * 获取类型数
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
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

        int type = getItemViewType(position);
        ViewHolder01 holder01 = null;
        ViewHolder02 holder02 = null;

        if (convertView == null) {
            switch (type) {
                case 0:
                    convertView = inflater.inflate(R.layout.item_listview, null);
                    holder01=new ViewHolder01();
                    holder01.name= (TextView) convertView.findViewById(R.id.item_list_txt_name);
                    holder01.intro= (TextView) convertView.findViewById(R.id.item_list_txt_intro);
                    holder01.picture= (ImageView) convertView.findViewById(R.id.item_list_img);
                    holder01.price= (TextView) convertView.findViewById(R.id.item_list_txt_price);
                    convertView.setTag(holder01);
                    break;
                case 1:
                    convertView=inflater.inflate(R.layout.layout_banner,null);
                    holder02=new ViewHolder02();
//                    holder02.banner= (BGABanner) convertView.findViewById(R.id.banner);
                    convertView.setTag(holder02);
                    break;
            }

        }else {
            switch (type){
                case 0:
                    holder01= (ViewHolder01) convertView.getTag();
                    holder01.name.setText("商品"+position);
                    holder01.price.setText("￥ "+mListData.get(position).getPrice());
                    holder01.picture.setImageResource(R.mipmap.ic_launcher);
                    holder01.intro.setText("123456578909876543421");
                    break;
                case 1:
                    holder02= (ViewHolder02) convertView.getTag();
            }
        }
        return convertView;
    }

    public class ViewHolder01 {

        private TextView name;
        private TextView intro;
        private ImageView picture;
        private TextView price;


    }

    public class ViewHolder02 {

//        private BGABanner banner;
    }
}
