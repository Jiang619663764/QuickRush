package com.example.huhu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.huhu.PayActivity;
import com.example.huhu.R;
import com.example.huhu.adpter.ShoppingAdapter;
import com.example.huhu.bean.ShoppingInfo;
import com.example.huhu.util.DBManager;
import com.example.huhu.view.ShoppingListView;

import java.util.List;

/**
 * 购物车页的Fragment
 * A simple {@link Fragment} subclass.
 */
public class ShoppingPageFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private List<ShoppingInfo> mShopInfo;

    private ShoppingListView mListView;

    private ShoppingAdapter mShopAdapter;

    private Button mPay;

    public ShoppingPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getData();
        View view = inflater.inflate(R.layout.fragment_shopping_page, container, false);
        mListView = (ShoppingListView) view.findViewById(R.id.listview_shopping_page);
        mPay= (Button) view.findViewById(R.id.pay_shopping_page);
        mShopAdapter = new ShoppingAdapter(getActivity(), mShopInfo);
        mListView.setAdapter(mShopAdapter);

        mShopAdapter.setOnAddNumListener(this);
        mShopAdapter.setOnSubNumListener(this);
        mShopAdapter.setOnDeleteListener(this);
        mListView.setOnItemClickListener(this);
        mPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PayActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
//        mShopInfo = new ArrayList<ShoppingInfo>();
//        for (int i = 0; i < 20; i++) {
//            ShoppingInfo info = new ShoppingInfo();
//            info.setUserName("name" + i);
//            info.setProDetail("Detail" + i);
//            info.setProCount(0);
//            info.setProPrice(10);
//            mShopInfo.add(info);
//        }
        DBManager db=new DBManager(getActivity());
        mShopInfo=db.query();

    }


    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        switch (v.getId()) {
            case R.id.btn_add_shopping_item:
                if (tag != null && tag instanceof Integer) { //解决问题：如何知道你点击的按钮是哪一个列表项中的，通过Tag的position
                    int position = (Integer) tag;
                    int num = mShopInfo.get(position).getProCount();
                    num++;
                    mShopInfo.get(position).setProCount(num);
                    mShopInfo.get(position).setProPrice(num * position);
                    mShopAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.btn_sub_shopping_item:
                if (tag != null && tag instanceof Integer) {
                    int position = (Integer) tag;
                    int num = mShopInfo.get(position).getProCount();
                    if (num > 0) {
                        num--;
                        mShopInfo.get(position).setProCount(num);
                        mShopInfo.get(position).setProPrice(num * position);
                        mShopAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.delete_shopping_item:
                if (tag != null && tag instanceof Integer) {
                    int position = (Integer) tag;
                    mShopInfo.remove(position);
                    mShopAdapter.notifyDataSetChanged();
                    mListView.turnToNormal();
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
