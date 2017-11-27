package com.bwie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.Presenter.ISouPresenter;
import com.bwie.Presenter.SouPresenter;
import com.bwie.View.ICardView;
import com.bwie.activity.DingDanActivity;
import com.bwie.adapter.CardApater;
import com.bwie.bean.ShopCardBean;
import com.bwie.jingdong.R;
import com.bwie.utils.ChildBean;
import com.bwie.utils.ParentBean;
import com.bwie.utils.TopBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${单巨廷} on 2017/10/31.
 */

public class Fragment_Card extends Fragment implements ICardView{
    @Bind(R.id.gouwuche_topbar)
    TopBar gouwucheTopbar;
    @Bind(R.id.card_expanded)
    ExpandableListView cardExpanded;
    @Bind(R.id.gouwuche_footer_check)
    CheckBox gouwucheFooterCheck;
    @Bind(R.id.gouwuche_footer_jiesuan)
    TextView gouwucheFooterJiesuan;
    @Bind(R.id.gouwuche_footer_price)
    TextView gouwucheFooterPrice;
    @Bind(R.id.gouwuche_footer_heji)
    TextView gouwucheFooterHeji;
    @Bind(R.id.gouwuche_footer)
    RelativeLayout gouwucheFooter;
    private View inflate;
    ShopCardBean shopCardBean;
    private ISouPresenter presenter;
    private List<ParentBean> card_01s = new ArrayList<>();
    private List<List<ChildBean>> listlist = new ArrayList<>();
    private CardApater cardApater;
    double sumprice = 0;//价格
    private List<ChildBean> card_02s;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.fragment4, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new SouPresenter(this);
        /**
         * 启动购物车  获取数据
         */
        presenter.getCardData(getActivity());

        /**
         * 全选监听
         */
        gouwucheFooterCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //选中状态
                boolean checked = gouwucheFooterCheck.isChecked();
                if (card_01s.size() != 0) {
                    //全选
                    all(checked);
                    return;
                }
                Toast.makeText(getActivity(), "空空也空空....",Toast.LENGTH_SHORT).show();            }
        });

        //结算
        gouwucheFooterJiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将选中的商品提交
                card_02s = new ArrayList<>();
                for (int i = 0; i < card_01s.size(); i++) {
                    for (int j = 0; j < listlist.get(i).size(); j++) {
                        ChildBean card02 = listlist.get(i).get(j);
                        if (card02.isCheck) {
                            card_02s.add(card02);
                        }
                    }
                }
                if (card_02s.size() == 0) {
                    Toast.makeText(getActivity(), "亲,你啥都没买哦...",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "我买了:" + card_02s.size() + "件商品,便宜点,亲",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DingDanActivity.class);
                intent.setAction("hhh");
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", (Serializable) card_02s);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                Log.i("card_02s", card_02s.size() + "");
            }
        });
    }

    //一级/二級  管理全选
    public void setAll(int position) {
        int index = 0;
        for (int i = 0; i < card_01s.size(); i++) {
            if (card_01s.get(i).isCheck) {
                index++;
            }
        }
        if (index == card_01s.size()) {
            gouwucheFooterCheck.setChecked(true);
        } else {
            gouwucheFooterCheck.setChecked(false);
        }
    }

    private void all(boolean checked) {
        sumprice = 0;
        //先要选中一级   在选中二级
        for (int i = 0; i < card_01s.size(); i++) {
            card_01s.get(i).setCheck(checked);
            //再一次遍历二级列表的数据
            for (int j = 0; j < listlist.get(i).size(); j++) {
                listlist.get(i).get(j).setCheck(checked);
                ChildBean card02 = listlist.get(i).get(j);
                double v = card02.getPrice() * card02.getSaleNum();
                sumprice = sumprice + v;
            }
        }
        cardApater.notifyDataSetChanged();
        //全部取消
        if (!checked) {
            //总价为零
            sumprice = 0;
        }
        gouwucheFooterPrice.setText("¥:" + sumprice);
        //将全选后的价格 价格适配器  用于单项选择
        cardApater.setSumprice(sumprice);
        Log.i("price:", sumprice + "");
    }

    /**
     * 获取数据
     *
     * @param shopCardBean
     */
    @Override
    public void getCardData(ShopCardBean shopCardBean) {
        this.shopCardBean = shopCardBean;
        if (shopCardBean.getData()== null) {
            //购物车是空的
            return;
        }
        Log.i("shopCardBean", shopCardBean.getData().size() + "");
        //将数据分为一级  二级
        List<ShopCardBean.DataBean> data = shopCardBean.getData();
        for (int i = 0; i < data.size(); i++) {
            ShopCardBean.DataBean dataBean = data.get(i);
            /**
             * 一级
             * 商户名  商户 id
             */
            String sellerName = dataBean.getSellerName();
        //    String sellerid = dataBean.getSellerid();
            ParentBean card_01 = new ParentBean(sellerName, false, true);
            card_01s.add(card_01);

            /**
             * 二级
             */
            List<ChildBean> card_02s = new ArrayList<>();
            for (int j = 0; j < dataBean.getList().size(); j++) {
                ShopCardBean.DataBean.ListBean listBean = dataBean.getList().get(j);
                ChildBean card_02 = new ChildBean(listBean.getTitle(),false,true,listBean.getNum(),listBean.getPrice(),listBean.getImages().split("\\|")[0] != null ? listBean.getImages() : listBean.getImages().split("\\|")[0]);
                card_02s.add(card_02);
            }
            listlist.add(card_02s);//二级
        }
        Log.i("listlist",listlist.get(0).get(0).getContent());
        cardApater = new CardApater(getActivity(), card_01s, listlist,this);
        cardExpanded.setAdapter(cardApater);
        for (int i = 0; i < card_01s.size(); i++) {
            cardExpanded.expandGroup(i);
        }
        cardExpanded.setGroupIndicator(null);

        //接口回调 一级
        cardApater.setiCard_01(new CardApater.ICard_01() {
            @Override
            public void setICard_01(int position) {
                setAll(position);
            }
        });
        //接口回调   二级
        cardApater.setiCard_02(new CardApater.ICard_02() {
            @Override
            public void setICrad_02(int position) {
                setAll(position);
            }
        });

        cardApater.setiNumPrice(new CardApater.INumPrice() {
            @Override
            public void isumPrice(double price) {
                gouwucheFooterPrice.setText("¥:" + price);
            }
        });

    }

    @Override
    public void delete(int i, int i1, String num, int select) {

        listlist.get(i).remove(i1);
        cardApater.notifyDataSetChanged();


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
