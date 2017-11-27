package com.bwie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.bwie.Presenter.ClassPresenter;
import com.bwie.Presenter.IClassPresenter;
import com.bwie.View.IClassView;
import com.bwie.adapter.LVAdapter;
import com.bwie.bean.OneClass;
import com.bwie.jingdong.R;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/10/31.
 */

public class Fragment_Class extends Fragment implements IClassView {
    private View view;
    private ListView mListView;
    private FrameLayout mFrameLayout;
    private IClassPresenter iClassPresenter;
    private List<OneClass.DataBean> data;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        initView(view);
        iClassPresenter=new ClassPresenter(this);
        iClassPresenter.oneClass();
        final Fragment getinstance = TwoClassFragment.getinstance(0);
        final FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,getinstance);
        fragmentTransaction.commit();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OneClass.DataBean dataBean = data.get(i);
                //获取id
                int cid = dataBean.getCid();
                //创建事务
                Fragment getinstance = TwoClassFragment.getinstance(cid);
                FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, getinstance);
                //进行提交
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    @Override
    public void oneClass(List<OneClass.DataBean> data) {
        if (data.size() == 0) {
            return;
        }
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < data.size(); i++) {
//            list.add(data.get(i).getName());
//            Log.i("xxx", data.get(i).getName());
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        LVAdapter adapter=new LVAdapter(data,getActivity());
        mListView.setAdapter(adapter);
        this.data = data;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.listView);
        mFrameLayout = (FrameLayout) view.findViewById(R.id.frameLayout);
    }
}
