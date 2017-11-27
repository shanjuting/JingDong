package com.bwie.View;

/**
 * Created by ${单巨廷} on 2017/11/1.
 */

public interface IRegisterView {
    void phoneError();
    void pwdError();
    void loginSuccess(String name);
    void loginError();
}
