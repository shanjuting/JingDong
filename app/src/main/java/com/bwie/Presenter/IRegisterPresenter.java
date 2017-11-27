package com.bwie.Presenter;

import android.content.Context;

/**
 * Created by ${单巨廷} on 2017/11/1.
 */

public interface IRegisterPresenter {
    void setInfoData(String name,String pwd);
    void setInfoLogin(String name, String pwd, Context context);
    void phoneError();
    void pwdError();
    void loginSuccess(String name);
    void loginError();
}
