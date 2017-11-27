package com.bwie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.activity.SouSuoActivity;
import com.bwie.bean.SouShow;
import com.bwie.jingdong.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/3.
 */

public class SouAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<SouShow.DataBean> list;
    SouSuoActivity souSuoActivity;

    public SouAdapter(Context context, List<SouShow.DataBean> list, SouSuoActivity souSuoActivity) {
        this.context = context;
        this.list = list;
        this.souSuoActivity = souSuoActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.sou_item, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(inflate);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                souSuoActivity.setListener(myViewHolder.getLayoutPosition());
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SouShow.DataBean dataBean = list.get(position);
        MyViewHolder holder1 = (MyViewHolder) holder;
        String title = dataBean.getTitle();
        holder1.shop_tv.setText(title);
        String[] images = dataBean.getImages().split("\\|");
        int price = (int) dataBean.getPrice();
        Picasso.with(context).load(images[0]).into(holder1.shop_iv);
        holder1.shop_price.setText("¥:"+price);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView shop_iv;
        private TextView shop_tv;
        private  TextView shop_price;

        public MyViewHolder(View itemView) {
            super(itemView);
            shop_iv = itemView.findViewById(R.id.shop_iv);
            shop_tv = itemView.findViewById(R.id.shop_tv);
            shop_price = itemView.findViewById(R.id.shop_price);
        }
    }
}
