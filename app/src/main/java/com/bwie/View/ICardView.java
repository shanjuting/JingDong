package com.bwie.View;

import com.bwie.bean.ShopCardBean;

/**
 * Created by ${单巨廷} on 2017/11/7.
 */

public interface ICardView {
    void getCardData(ShopCardBean shopCardBean);
    void delete(int i,int i1,String num,int select);
}
