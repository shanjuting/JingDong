
package com.bwie.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.View.ICardView;
import com.bwie.jingdong.R;
import com.bwie.utils.ChildBean;
import com.bwie.utils.ParentBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.bwie.jingdong.R.layout.card_01;

;


public class CardApater extends BaseExpandableListAdapter {
    Context context;
    List<ParentBean> card_01s;
    List<List<ChildBean>> listlist;
    ICardView iCardView;
    boolean n=false;

    public void setSumprice(double sumprice) {
        this.sumprice = sumprice;
    }

    double sumprice = 0;

    public CardApater(Context context, List<ParentBean> card_01s, List<List<ChildBean>> listlist,ICardView iCardView) {
        this.context = context;
        this.card_01s = card_01s;
        this.listlist = listlist;
        this.iCardView=iCardView;
    }

    @Override
    public int getGroupCount() {
        return card_01s.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listlist.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return card_01s.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listlist.get(i).get(i1);
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
        return true;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        final ParentBean parentBean= (ParentBean) getGroup(i);
        final Card01 card01;
        if (view == null) {
            card01 = new Card01();
            view = View.inflate(context, card_01, null);
            card01.card01check = view.findViewById(R.id.cart_01_check);
            card01.card01name = view.findViewById(R.id.cart_01_shopname);
            card01.card01edit = view.findViewById(R.id.cart_01_edit);
            view.setTag(card01);
            card01.card01check.setTag(parentBean);
        } else {
            card01 = (Card01) view.getTag();
            card01.card01check.setTag(parentBean);
        }
       // final Card_01 card_01 = card_01s.get(i);

        //设置状态
        card01.card01check.setChecked(parentBean.isCheck());
        card01.card01name.setText(parentBean.getTitle());
        card01.card01check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取点击后的状态
                boolean checked = card01.card01check.isChecked();
                //修改对象 数据
                parentBean.setCheck(checked);
                //根据一级选择二级
                select02(i, checked);
                //接口回调  一级管理全选
                iCard_01.setICard_01(i);
            }
        });

        card01.card01edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ParentBean parentBean1 = (ParentBean) card01.card01check.getTag();
                if(parentBean1.ziCheck)
                {
                    parentBean1.ziCheck=false;
                    //去修改二级列表的boolean
                    ziChangeBianji(i,parentBean1.ziCheck);
                }
                else
                {
                    parentBean1.ziCheck=true;
                    //去修改二级列表的boolean
                    ziChangeBianji(i,parentBean1.ziCheck);
                }
                notifyDataSetChanged();

            }
        });
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        final Card02 card02;
        if (view == null) {
            card02 = new Card02();
            view = View.inflate(context,R.layout. card_02, null);
            card02.card02check = view.findViewById(R.id.cart_02_check);
            card02.card02name = view.findViewById(R.id.cart_02_title);
            card02.card02price = view.findViewById(R.id.cart_02_price);
            card02.card02num = view.findViewById(R.id.cart_02_salenum);
            card02.card02image = view.findViewById(R.id.cart_02_image);
            card02.layout = view.findViewById(R.id.cart_expand_2_linear);
            card02.relativeLayout=view.findViewById(R.id.cart_02_relative);
            card02. cart_expand_2_jian = view.findViewById(R.id.cart_expand_2_jian);
            card02. cart_expand_2_add = view.findViewById(R.id.cart_expand_2_add);
            card02.button=view.findViewById(R.id.cart_02_del);
            view.setTag(card02);
        } else {
            card02 = (Card02) view.getTag();
        }
        //暂时隐藏
        boolean n = this.n;

         if(n){
             card02.relativeLayout.setVisibility(View.GONE);
             Log.i("dsdsdsds",n+"");
             Log.i("ss","sdfg");
             card02.layout.setVisibility(View.VISIBLE);

         }else{
             card02.layout.setVisibility(View.GONE);
             card02.relativeLayout.setVisibility(View.VISIBLE);
         }
        final ChildBean card_02 = listlist.get(i).get(i1);


        if(card_02.viewChange)
        {
            card02.layout.setVisibility(View.GONE);
            card02.relativeLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            card02.layout.setVisibility(View.VISIBLE);
            card02.relativeLayout.setVisibility(View.GONE);
        }


        //选择框
        card02.card02check.setChecked(card_02.isCheck());
        //数量
        card02.card02num.setText("X" + card_02.getSaleNum());
        //价格
        card02.card02price.setText("¥:" + card_02.getPrice());
        //名称
        card02.card02name.setText("" + card_02.getContent());
        //商品图片
        Picasso.with(context).load(card_02.getImageUrl()).placeholder(R.mipmap.gouwuche1).into(card02.card02image);
        card02.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("delete",111+"");
                //接口回调进行删除
                iCardView.delete(i,i1,1+"",0);
            }
        });


        card02.card02check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取选中状态
                boolean checked = card02.card02check.isChecked();
                //修改Bean 数据的状态
                card_02.setCheck(checked);
                //通过管理  一级条目的 状态
                card01(i, i1);
                //接口回调  二级管理全选
                iCard_02.setICrad_02(i);
                //单个价格
                double v = card_02.getPrice() * card_02.getSaleNum();
                //true +钱   false - 钱
                if (checked) {
                    sumprice = sumprice + v;
                } else {
                    sumprice = sumprice - v;
                }
                //最终价格    接口回调
                iNumPrice.isumPrice(sumprice);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class Card01 {
        CheckBox card01check;
        TextView card01name;
        TextView card01edit;
    }

    class Card02 {
        CheckBox card02check;
        TextView card02name;
        TextView card02price;
        TextView card02num;
        ImageView card02image;
        LinearLayout layout;
        RelativeLayout relativeLayout;
        Button button;
        Button  cart_expand_2_jian;
        Button cart_expand_2_add;
    }

    //改变编辑状态的方法
    private void ziChangeBianji(int position,boolean flag)
    {
        //修改子布局
        List<ChildBean> childBeen=listlist.get(position);
        for (int i=0;i<childBeen.size();i++)
        {
            ChildBean bean=childBeen.get(i);
            bean.viewChange=flag;
        }
    }
    /**
     * 二级  管理 一级
     *
     * @param i
     * @param i1
     */
    public void card01(int i, int i1) {
        int index = 0;
        //二级列表的数据
        List<ChildBean> card_02s = listlist.get(i);
        //false一级不选中   true 一级选中
        for (ChildBean card02 : card_02s) {
            if (card02.isCheck()) {
                index++;
            }
        }
        if (index == card_02s.size()) {
            //一级全部选中
            card_01s.get(i).setCheck(true);
        } else {
            //一级全部不选中
            card_01s.get(i).setCheck(false);
        }
        //将数据展示  刷新
        notifyDataSetChanged();
    }

    /**
     * 一级 管理 二级
     *
     * @param i
     */
    public void select02(int i, boolean b) {
        if (b) {
            card02(b, i);
            return;
        }
        card02(b, i);
    }

    //判断子条目是否选中
    private void card02(boolean is, int i) {
        double num = 0;
        //如果一级选中对应的二级全部选中
        List<ChildBean> card_02s = listlist.get(i);

        if (!is) {
            //取消选中  减去所有子条目商品的价格
            for (ChildBean card02 : card_02s) {
                //二级列表的商品价格
                double v = card02.getPrice() * card02.getSaleNum();
                //全部
                num = +v;
                sumprice = sumprice - num;
                card02.setCheck(is);
            }
        }else{
            //一级选中    获取所有子条目商品的价格
            for (ChildBean card02 : card_02s) {
                //二级列表的商品价格
                double v = card02.getPrice() * card02.getSaleNum();
                //全部
                num = +v;
                sumprice = sumprice + num;
                card02.setCheck(is);
            }
        }

        //最终价格     接口回调
        iNumPrice.isumPrice(sumprice);
        Log.i("price", sumprice + "");
        notifyDataSetChanged();
    }

    /**
     * 接口回调  一级  关联  全选
     */

    private ICard_01 iCard_01;

    public interface ICard_01 {
        void setICard_01(int position);
    }

    ;

    public void setiCard_01(ICard_01 iCard_01) {
        this.iCard_01 = iCard_01;
    }

    /**
     * 接口回调  二级  关联  全选
     */
    private ICard_02 iCard_02;

    public interface ICard_02 {
        void setICrad_02(int position);
    }

    public void setiCard_02(ICard_02 iCard_02) {
        this.iCard_02 = iCard_02;
    }


    /**
     * 接口回調  总价
     */
    public INumPrice iNumPrice;

    public interface INumPrice {
        void isumPrice(double price);
    }

    public void setiNumPrice(INumPrice iNumPrice) {
        this.iNumPrice = iNumPrice;
    }
}
