package com.example.huhu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.huhu.GoodsWebActivity;
import com.example.huhu.R;
import com.example.huhu.adpter.ListViewAdapter02;
import com.example.huhu.bean.FirstPageInfo;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页的Fragment
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private PullToRefreshListView mReFreshListView;

    private ListViewAdapter02 mListAdapter;

    private List<FirstPageInfo> mListData;

    private SliderLayout mSliderLayout;

    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getData();
        View view=inflater.inflate(R.layout.fragment_home_page, container, false);

        mReFreshListView= (PullToRefreshListView) view.findViewById(R.id.refreshListView_home_page);

        ListView listView = mReFreshListView.getRefreshableView();

        View layout= LayoutInflater.from(getActivity()).inflate(R.layout.layout_banner,null);

        //轮播图的设定
        mSliderLayout= (SliderLayout) layout.findViewById(R.id.slider);
        addPicture();
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSliderLayout.setDuration(3000);
        listView.addHeaderView(layout);

        mListAdapter = new ListViewAdapter02(getActivity(), mListData);

        mReFreshListView.setAdapter(mListAdapter);

        mReFreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), GoodsWebActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

        mReFreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                mReFreshListView.onRefreshComplete();
            }
        });
        return view;
    }

    private void addPicture() {
        TextSliderView tsv=new TextSliderView(getActivity());
        tsv.image(R.mipmap.pic1);
        tsv.description("动漫：01");
        mSliderLayout.addSlider(tsv);
        TextSliderView tsv1=new TextSliderView(getActivity());
        tsv1.image(R.mipmap.pic2);
        tsv1.description("动漫：02");
        mSliderLayout.addSlider(tsv1);
        TextSliderView tsv2=new TextSliderView(getActivity());
        tsv2.image(R.mipmap.pic3);
        tsv2.description("动漫：03");
        mSliderLayout.addSlider(tsv2);
    }

    private void getData() {
        mListData = new ArrayList<FirstPageInfo>();
        for (int i = 0; i < 9; i++) {
            FirstPageInfo info = new FirstPageInfo();
            info.setName("1");
            info.setPrice(20);
            info.setIntro("1233");
            mListData.add(info);
        }
    }


}
