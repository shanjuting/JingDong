package com.bwie.Presenter;

import android.content.Context;

import com.bwie.Model.IRegisterModel;
import com.bwie.Model.RegisterModel;
import com.bwie.View.IRegisterView;

/**
 * Created by ${单巨廷} on 2017/11/1.
 */

public class RegisterPresenter implements IRegisterPresenter{

    private IRegisterView iRegisterView;
    private IRegisterModel iRegisterModel;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        iRegisterModel = new RegisterModel();
    }

    @Override
    public void setInfoData(String name, String pwd) {
             iRegisterModel.setInfoData(name,pwd,this);
    }

    @Override
    public void setInfoLogin(String name, String pwd, Context context) {
             iRegisterModel.setInfoLogin(name,pwd,this,context);
    }

    @Override
    public void phoneError() {
            iRegisterView.phoneError();
    }

    @Override
    public void pwdError() {
            iRegisterView.pwdError();
    }

    @Override
    public void loginSuccess(String name) {
            iRegisterView.loginSuccess(name);
    }

    @Override
    public void loginError() {
            iRegisterView.loginError();
    }
}
