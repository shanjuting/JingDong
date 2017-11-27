package com.bwie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.bean.OneClass;
import com.bwie.jingdong.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/9.
 */

public class LVAdapter extends BaseAdapter{

    private List<OneClass.DataBean> data;
    private Context context;

    public LVAdapter(List<OneClass.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view=View.inflate(context, R.layout.left_item,null);

        ImageView imageView=(ImageView) view.findViewById(R.id.left_image2);

        TextView textView=(TextView) view.findViewById(R.id.title_tvv);

        textView.setText(data.get(i).getName());

        Picasso.with(context).load(data.get(i).getIcon()).into(imageView);

        return view;
    }
}
