package com.bwie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.bean.TwoClass;
import com.bwie.jingdong.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/5.
 */

public class ThirdAdapter extends BaseAdapter {

    Context context;
    List<TwoClass.DataBean.ListBean> list;

    public ThirdAdapter(Context context, List<TwoClass.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.third_item, null);
            holder.head_iv = view.findViewById(R.id.head_iv);
            holder.title_tv = view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TwoClass.DataBean.ListBean listBean = list.get(i);
        String name = listBean.getName();
        holder.title_tv.setText(name);
        Picasso.with(context).load(R.mipmap.icon).into(holder.head_iv);
        return view;
    }

    class ViewHolder {
        ImageView head_iv;
        TextView title_tv;
    }
}