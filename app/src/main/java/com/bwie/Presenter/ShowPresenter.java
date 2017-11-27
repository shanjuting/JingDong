package com.bwie.Presenter;

import com.bwie.Model.IShowModel;
import com.bwie.Model.ShowModel;
import com.bwie.View.IShowView;
import com.bwie.bean.ShowPage;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/2.
 */

public class ShowPresenter implements IShowPresenter{

    private IShowView iShowView;
    private IShowModel iShowModel;

    public ShowPresenter(IShowView iShowView) {
        this.iShowView = iShowView;
        iShowModel=new ShowModel();
    }

    @Override
    public void getData() {
        iShowModel.getData(this);
    }

    @Override
    public void getData(List<ShowPage.DataBean> dataBeen, ShowPage.MiaoshaBean miaoshaBean, ShowPage.TuijianBean tuijianBean) {
       iShowView.getData(dataBeen,miaoshaBean,tuijianBean);
    }
}
