package com.bwie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.bwie.Presenter.ITwoClassPresenter;
import com.bwie.Presenter.TwoClassPresenter;
import com.bwie.View.ITwoClass;
import com.bwie.adapter.Expan;
import com.bwie.bean.TwoClass;
import com.bwie.jingdong.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/5.
 */

public class TwoClassFragment extends Fragment implements ITwoClass{
    private View view;
    private ExpandableListView mExpandableListView;
    private ITwoClassPresenter presenter;
    private Expan expan;
    private List<String> list;
    private Banner banner;
     public final static String Ban[] = new String[]{
             "http://img1.imgtn.bdimg.com/it/u=3496212285,3924381008&fm=27&gp=0.jpg",
             "http://img2.imgtn.bdimg.com/it/u=4080250095,1716255945&fm=27&gp=0.jpg",
             "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2276928917,3787381314&fm=27&gp=0.jpg",
             "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1314106643,146811581&fm=27&gp=0.jpg"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twoclass, container, false);
        initView(view);
        Bundle bundle = getArguments();
        int cid = bundle.getInt("cid");
        presenter=new TwoClassPresenter(this);
        presenter.twoClass(cid);
        banner = (Banner) view.findViewById(R.id.banner);
        list = new ArrayList<>();
        for (int i = 0; i < Ban.length; i++){
            list.add(Ban[i]);
        }

//无限轮播
//设置加载样式
        banner.setIndicatorGravity(Banner.CENTER);
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
//是否自动轮播
        banner.isAutoPlay(true);
//设置轮播的时间间隔
        banner.setDelayTime(3000);
//设置加载的图片，解析出来的url
        banner.setImages(list);
        return view;
    }
    public static Fragment getinstance(int cid) {
        TwoClassFragment fragment = new TwoClassFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cid", cid);
        fragment.setArguments(bundle);
        return fragment;
    }
    private void initView(View view) {
        mExpandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
    }

    @Override
    public void twoClass(List<TwoClass.DataBean> twodata) {
        if (twodata.size() == 0) {
            return;
        }
        if (expan == null) {
            expan = new Expan(getActivity(),twodata);
            mExpandableListView.setAdapter(expan);
        } else {
            expan.notifyDataSetChanged();
        }
        for (int i = 0; i < twodata.size(); i++) {
            mExpandableListView.expandGroup(i);
        }
        mExpandableListView.setGroupIndicator(null);
    }
}
