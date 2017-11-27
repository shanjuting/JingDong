package com.bwie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.jingdong.R;
import com.bwie.utils.ChildBean;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/10.
 */

public class MyAdater extends BaseAdapter {

    private List<ChildBean> card_02s1;
    private Context context;

    public MyAdater(List<ChildBean> card_02s1, Context context) {
        this.card_02s1 = card_02s1;
        this.context = context;
    }

    @Override
    public int getCount() {
        return card_02s1.size();
    }

    @Override
    public Object getItem(int i) {
        return card_02s1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DViewHolder dViewHolder ;
        if (view==null){
            dViewHolder = new DViewHolder();
            view = View.inflate(context,R.layout.dingdan_item,null);
            dViewHolder.dd_iv = view.findViewById(R.id.dd_iv);
            dViewHolder.title = view.findViewById(R.id.dd_title);
            dViewHolder.num = view.findViewById(R.id.dd_num);
            dViewHolder.price = view.findViewById(R.id.dd_price);
            view.setTag(dViewHolder);
        }else{
            dViewHolder = (DViewHolder) view.getTag();
        }

        ChildBean card02 = card_02s1.get(i);
        dViewHolder.num.setText("X"+card02.getSaleNum());
        dViewHolder.title.setText(""+card02.getContent());
        dViewHolder.price.setText("¥:"+card02.getPrice());
        return view;
    }
}

class DViewHolder {
    ImageView dd_iv;
    TextView title;
    TextView num;
    TextView price;
}
