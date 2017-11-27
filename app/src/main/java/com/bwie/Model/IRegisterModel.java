package com.bwie.Model;

import android.content.Context;

import com.bwie.Presenter.IRegisterPresenter;

/**
 * Created by ${单巨廷} on 2017/11/1.
 */

public interface IRegisterModel {
    void setInfoData(String name, String pwd, IRegisterPresenter iRegisterPresenter);
    void setInfoLogin(String name, String pwd, IRegisterPresenter iRegisterPresenter, Context context);
}
