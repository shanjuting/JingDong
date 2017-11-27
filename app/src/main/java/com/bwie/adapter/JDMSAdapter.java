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

public class JDMSAdapter extends BaseAdapter{

    private List<ShowPage.MiaoshaBean.ListBeanX> miaoshaBeanList;
    private Context context;

    public JDMSAdapter(List<ShowPage.MiaoshaBean.ListBeanX> miaoshaBeanList, Context context) {
        this.miaoshaBeanList = miaoshaBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return miaoshaBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return miaoshaBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view=View.inflate(context, R.layout.ti_item,null);

        ImageView imageView=(ImageView) view.findViewById(R.id.jdmsmage);

        TextView textView=(TextView) view.findViewById(R.id.jdmstext);

        String images = miaoshaBeanList.get(i).getImages();

        String[] split = images.split("\\|");

        String s = split[0];

        Picasso.with(context).load(s).into(imageView);

        textView.setText(miaoshaBeanList.get(i).getTitle());

        return view;
    }
}
