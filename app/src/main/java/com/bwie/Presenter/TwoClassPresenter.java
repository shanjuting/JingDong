package com.bwie.Presenter;

import com.bwie.Model.ITwoClassModel;
import com.bwie.Model.TwoClassModel;
import com.bwie.View.ITwoClass;
import com.bwie.bean.TwoClass;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/5.
 */

public class TwoClassPresenter implements ITwoClassPresenter{

    private ITwoClass iTwoClass;
    private ITwoClassModel iTwoClassModel;

    public TwoClassPresenter(ITwoClass iTwoClass) {
        this.iTwoClass = iTwoClass;
        iTwoClassModel=new TwoClassModel();
    }

    @Override
    public void twoClass(int cid) {
        iTwoClassModel.twoclass(cid,this);
    }

    @Override
    public void twoClass(List<TwoClass.DataBean> twolist) {
        iTwoClass.twoClass(twolist);
    }
}
