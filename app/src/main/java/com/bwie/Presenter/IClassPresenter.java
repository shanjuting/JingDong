package com.bwie.Presenter;

import com.bwie.bean.OneClass;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/5.
 */

public interface IClassPresenter {
    void oneClass();

    void oneClass(List<OneClass.DataBean> data);
}
