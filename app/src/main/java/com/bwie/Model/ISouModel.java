package com.bwie.Model;

import android.content.Context;

import com.bwie.Presenter.ISouPresenter;

/**
 * Created by ${单巨廷} on 2017/11/3.
 */

public interface ISouModel {

    //需要刷新   传一个 page   商品名称
    void infoData(String name,int page, ISouPresenter presenter);

    //购物车add
    void addCard(int uid,int pid,int sellerid,ISouPresenter presenter);

    //查询购物车
    void selectCard(Context context, ISouPresenter presenter);
}
