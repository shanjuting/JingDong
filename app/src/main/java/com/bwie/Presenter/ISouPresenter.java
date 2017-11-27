package com.bwie.Presenter;

import android.content.Context;

import com.bwie.bean.ShopCardBean;
import com.bwie.bean.SouShow;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/3.
 */

public interface ISouPresenter {

    //需要刷新 传一个 page   商品名称
    void infoData(String name,int page);

    void infoData(List<SouShow.DataBean> data);

    //购物车add
    void addCard(int uid,int pid,int sellerid);


    /**
     * 购物车
     */
    void getCardData(Context context);

    /**
     * 获取购物车的数据
     */
    void getCardData(ShopCardBean shopCardBean);

}
