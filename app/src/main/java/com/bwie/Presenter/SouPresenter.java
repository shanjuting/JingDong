package com.bwie.Presenter;

import android.content.Context;

import com.bwie.Model.ISouModel;
import com.bwie.Model.SouModel;
import com.bwie.View.IAddCardView;
import com.bwie.View.ICardView;
import com.bwie.View.ISousou;
import com.bwie.bean.ShopCardBean;
import com.bwie.bean.SouShow;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/3.
 */

public class SouPresenter implements ISouPresenter{

    private ISousou iSousou;
    private ISouModel iSouModel=new SouModel();;

    public SouPresenter(ISousou iSousou) {
        this.iSousou = iSousou;
    }


    @Override
    public void infoData(String name, int page) {
           iSouModel.infoData(name,page,this);
    }

    @Override
    public void infoData(List<SouShow.DataBean> data) {
           iSousou.infodata(data);
    }

    private IAddCardView iAddCardView;

    public SouPresenter(IAddCardView iAddCardView) {
        this.iAddCardView = iAddCardView;
    }
    @Override
    public void addCard(int uid, int pid, int sellerid) {
        iSouModel.addCard(uid,pid,sellerid,this);
    }

    private ICardView iCardView;

    public SouPresenter(ICardView iCardView) {
        this.iCardView = iCardView;
    }

    @Override
    public void getCardData(Context context) {
          iSouModel.selectCard(context,this);
    }

    @Override
    public void getCardData(ShopCardBean shopCardBean) {
       iCardView.getCardData(shopCardBean);
    }
}
