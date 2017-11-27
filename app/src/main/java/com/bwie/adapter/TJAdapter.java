package com.bwie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.bean.ShowPage;
import com.bwie.jingdong.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/9.
 */

public class TJAdapter extends BaseAdapter{

    private List<ShowPage.TuijianBean.ListBean> list;
    private Context context;

    public TJAdapter(List<ShowPage.TuijianBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
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

        view=View.inflate(context, R.layout.tj_item,null);

        ImageView imageView=(ImageView) view.findViewById(R.id.tjimage);

        TextView textView=(TextView) view.findViewById(R.id.tjtext);

        String images = list.get(i).getImages();

        String[] split = images.split("\\|");

        String s = split[0];

        Picasso.with(context).load(s).into(imageView);

        textView.setText(list.get(i).getTitle());

        return view;
    }
}
