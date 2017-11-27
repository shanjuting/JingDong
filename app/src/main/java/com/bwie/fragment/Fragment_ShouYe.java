package com.bwie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.Presenter.IShowPresenter;
import com.bwie.Presenter.ShowPresenter;
import com.bwie.View.IShowView;
import com.bwie.activity.EWMActivity;
import com.bwie.activity.SouSuoActivity;
import com.bwie.adapter.JDMSAdapter;
import com.bwie.adapter.TJAdapter;
import com.bwie.bean.ShowPage;
import com.bwie.jingdong.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${单巨廷} on 2017/10/31.
 */

public class Fragment_ShouYe extends Fragment implements View.OnClickListener, IShowView {
    private View view;
    private Banner mBanner;
    /**
     * 扫啊扫
     */
    private CheckBox mEw;
    /**
     * 京东闪购,问你来不来
     */
    private TextView mEt;
    private LinearLayout mHeadLayout;
    private IShowPresenter iShowPresenter;
    private ListView mListView;
    private ListView mListView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        initView(view);
        iShowPresenter = new ShowPresenter(this);
        iShowPresenter.getData();
        return view;
    }


    private void initView(View view) {
        mBanner = (Banner) view.findViewById(R.id.banner);
        mEw = (CheckBox) view.findViewById(R.id.ew);
        mEw.setOnClickListener(this);
        mEt = (TextView) view.findViewById(R.id.et);
        mEt.setOnClickListener(this);
        mHeadLayout = (LinearLayout) view.findViewById(R.id.head_layout);
        mListView = (ListView) view.findViewById(R.id.listView);
        mListView2 = (ListView) view.findViewById(R.id.listView2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ew:
                Intent it2 = new Intent(getActivity(), EWMActivity.class);
                startActivity(it2);
                break;
            case R.id.et:
                Intent it = new Intent(getActivity(), SouSuoActivity.class);
                startActivity(it);
                break;
        }
    }

    @Override
    public void getData(List<ShowPage.DataBean> dataBeen, ShowPage.MiaoshaBean miaoshaBean, ShowPage.TuijianBean tuijianBean) {
        List<String> blist = new ArrayList<>();
        for (int i = 0; i < dataBeen.size(); i++) {
            blist.add(dataBeen.get(i).getIcon());
        }
        setmBanner(blist);

        List<ShowPage.TuijianBean.ListBean> tjlist = tuijianBean.getList();
        TJAdapter tjAdapter = new TJAdapter(tjlist, getActivity());
        mListView.setAdapter(tjAdapter);

        List<ShowPage.MiaoshaBean.ListBeanX> miaoshaBeanList = miaoshaBean.getList();
        JDMSAdapter jdmsAdapter = new JDMSAdapter(miaoshaBeanList, getActivity());
        mListView2.setAdapter(jdmsAdapter);
    }

    private void setmBanner(List<String> blist) {
        if (blist == null) {
            Toast.makeText(getActivity(), "轮播你的错啦", Toast.LENGTH_SHORT).show();
            return;
        }
        //设置加载样式
        mBanner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        //是否自动轮播
        mBanner.isAutoPlay(true);
        //设置轮播的时间间隔
        mBanner.setDelayTime(3000);
        //设置加载的图片，解析出来的url
        mBanner.setImages(blist);
    }

}
