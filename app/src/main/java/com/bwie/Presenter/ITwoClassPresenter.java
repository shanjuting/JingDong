package com.bwie.Presenter;

import com.bwie.bean.TwoClass;

import java.util.List;

/**
 * Created by ${单巨廷} on 2017/11/5.
 */

public interface ITwoClassPresenter {

    void twoClass(int cid);

    void twoClass(List<TwoClass.DataBean> twolist);

}
