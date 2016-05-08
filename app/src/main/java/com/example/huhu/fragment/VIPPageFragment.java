package com.example.huhu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.huhu.R;
import com.example.huhu.adpter.ListViewAdapter02;
import com.example.huhu.bean.FirstPageInfo;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


/**
 * VIP页的Fragment
 * A simple {@link Fragment} subclass.
 */
public class VIPPageFragment extends Fragment implements PullToRefreshBase.OnRefreshListener{

    private PullToRefreshListView refreshListView;

    private ListViewAdapter02 mListAdapter;

    private List<FirstPageInfo> mListData;

    private GridView gridView;

    private SliderLayout mSlider;

    public VIPPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getData();

        View view=inflater.inflate(R.layout.fragment_vippage, container, false);
        refreshListView= (PullToRefreshListView) view.findViewById(R.id.refreshListView_vip_page);
        ListView listView = refreshListView.getRefreshableView();

        View layout= LayoutInflater.from(getActivity()).inflate(R.layout.layout_banner, null);

        //轮播图的设定
        mSlider= (SliderLayout) layout.findViewById(R.id.slider);
        addPicture();
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSlider.setDuration(3000);

        listView.addHeaderView(layout);

        mListAdapter = new ListViewAdapter02(getActivity(), mListData);

        refreshListView.setAdapter(mListAdapter);
        return view;
    }

    private void addPicture() {
        TextSliderView tsv=new TextSliderView(getActivity());
        tsv.image(R.mipmap.yinghua);
        tsv.description("动漫：樱花");
        mSlider.addSlider(tsv);
        TextSliderView tsv1=new TextSliderView(getActivity());
        tsv1.image(R.mipmap.suolong);
        tsv1.description("动漫：索隆");
        mSlider.addSlider(tsv1);
        TextSliderView tsv2=new TextSliderView(getActivity());
        tsv2.image(R.mipmap.meinv);
        tsv2.description("动漫：美女");
        mSlider.addSlider(tsv2);
    }


    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        refreshListView.onRefreshComplete();
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

    @Override
    public void onStop() {
        mSlider.stopAutoCycle();
        super.onStop();
    }
}
