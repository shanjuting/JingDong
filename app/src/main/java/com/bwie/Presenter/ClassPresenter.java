package com.bwie.Presenter;

import com.bwie.Model.ClassModel;
import com.bwie.Model.IClassModel;
import com.bwie.View.IClassView;
import com.bwie.bean.OneClass;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/5.
 */

public class ClassPresenter implements IClassPresenter{

    private IClassView iClassView;
    private IClassModel iClassModel;

    public ClassPresenter(IClassView iClassView) {
        this.iClassView = iClassView;
        iClassModel=new ClassModel();
    }

    @Override
    public void oneClass() {
       iClassModel.oneClass(this);
    }

    @Override
    public void oneClass(List<OneClass.DataBean> data) {
        iClassView.oneClass(data);
    }
}
