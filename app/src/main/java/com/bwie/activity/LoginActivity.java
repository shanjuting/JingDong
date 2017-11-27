package com.bwie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.Presenter.IRegisterPresenter;
import com.bwie.Presenter.RegisterPresenter;
import com.bwie.View.IRegisterView;
import com.bwie.bean.MessageEvent;
import com.bwie.jingdong.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends AppCompatActivity implements IRegisterView,View.OnClickListener {

    private ImageView mDenglufanhui;
    /**
     * 请输入账号
     */
    private EditText mUserName;
    /**
     * 请输入密码
     */
    private EditText mUserPwd;
    /**
     * 登录
     */
    private Button mLoginBtn;
    /**
     * 注册登录
     */
    private TextView mRegister;
    /**
     * 找回密码
     */
    private TextView mZhaohui;

    private IRegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initView();
        presenter=new RegisterPresenter(this);

        EventBus.getDefault().register(this);
    }

    private void initView() {
        mDenglufanhui = (ImageView) findViewById(R.id.denglufanhui);
        mDenglufanhui.setOnClickListener(this);
        mUserName = (EditText) findViewById(R.id.user_name);
        mUserPwd = (EditText) findViewById(R.id.user_pwd);
        mLoginBtn = (Button) findViewById(R.id.login_btn);
        mLoginBtn.setOnClickListener(this);
        mRegister = (TextView) findViewById(R.id.register);
        mRegister.setOnClickListener(this);
        mZhaohui = (TextView) findViewById(R.id.zhaohui);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.denglufanhui:
                finish();
                break;
            case R.id.login_btn:
                String name = mUserName.getText().toString();
                String pwd = mUserPwd.getText().toString();
                presenter.setInfoLogin(name,pwd,this);
                break;
            case R.id.register:
                Intent it=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(it);
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEvent(MessageEvent event) {
        mUserName.setText(event.getName());
        Log.i("xxx",event.getName());
    }



    @Override
    public void phoneError() {
        Toast.makeText(this,"手机号不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdError() {
        Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(String name) {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void loginError() {
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
