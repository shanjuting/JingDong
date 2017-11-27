package com.bwie.View;

import com.bwie.bean.ShowPage;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/2.
 */

public interface IShowView {
    void getData(List<ShowPage.DataBean> dataBeen,ShowPage.MiaoshaBean miaoshaBean,ShowPage.TuijianBean tuijianBean);
}
