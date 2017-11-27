package com.bwie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.bean.TwoClass;
import com.bwie.jingdong.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/5.
 */

public class Expan extends BaseExpandableListAdapter{

    private Context context;
    private List<TwoClass.DataBean> data;

    public Expan(Context context, List<TwoClass.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return i;
    }

    @Override
    public Object getGroup(int i) {
        return data.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return data.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.two_item, null);
        TextView view1 = view.findViewById(R.id.title_tv);
        ImageView imageView=view.findViewById(R.id.left_image);
        Picasso.with(context).load(R.mipmap.icon).into(imageView);
        String name = data.get(i).getName();
        view1.setText(name);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.third, null);
        GridView view1 = view.findViewById(R.id.gridView);
        ThirdAdapter adater = new ThirdAdapter(context, data.get(i).getList());
        view1.setAdapter(adater);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
