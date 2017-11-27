package com.bwie.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.bean.SouShow;
import com.bwie.jingdong.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ${单巨廷} on 2017/11/7.
 */

public class CartPopupWindow extends PopupWindow {
    private View mView;

    private ImageView imageView;
    private TextView price, ok, goodnum;
    private Button add, jian;
    SouShow.DataBean dataBean;
    private int sum = 0;
    private TextView addcart_pop_kucun;

    public CartPopupWindow(Activity context, SouShow.DataBean dataBean) {
        super(context);
        this.dataBean = dataBean;
        mView = View.inflate(context, R.layout.addcart_pop, null);
        //初始化控件
        initView(mView);
        price.setText(dataBean.getPrice()+"￥");
        addcart_pop_kucun.setText(dataBean.getSalenum()+"件商品");
        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(400);
        Log.i("wyb", "width----->" + getWidth() + "");
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.mypopwindow_anim_style);

        this.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        createEvent(context);

        Picasso.with(context).load(dataBean.getImages().split("\\|")[0]).into(imageView);
    }

    private void initView(View view) {
        imageView = (ImageView) view.findViewById(R.id.addcart_pop_image);
        price = (TextView) view.findViewById(R.id.addcart_pop_price);
        goodnum = (TextView) view.findViewById(R.id.addcart_pop_goodnum);
        add = (Button) view.findViewById(R.id.addcart_pop_add);
        jian = (Button) view.findViewById(R.id.addcart_pop_jian);
        ok = (TextView) view.findViewById(R.id.addcart_pop_ok);
        addcart_pop_kucun = view.findViewById(R.id.addcart_pop_kucun);

    }
    public void set(Context context){
        Picasso.with(context).load(dataBean.getImages().split("\\|")[0]);
    }

    private void createEvent(final Activity context) {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(goodnum.getText().toString());
                num++;
                goodnum.setText(num + "");
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(goodnum.getText().toString());
                num--;
                if (num < 1) {
                    Toast.makeText(context, "数量不能小于1", Toast.LENGTH_SHORT).show();
                    return;
                }
                goodnum.setText(num + "");
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加购物车
                int num = Integer.parseInt(goodnum.getText().toString());
                ishop.num(num);
                dismiss();
            }
        });
    }

    public Ishop ishop;
    public interface Ishop{
        void num(int num);
    }
    public void setIshop(Ishop ishop) {
        this.ishop = ishop;
    }
}
