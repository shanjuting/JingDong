package com.bwie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.Presenter.IRegisterPresenter;
import com.bwie.Presenter.RegisterPresenter;
import com.bwie.View.IRegisterView;
import com.bwie.bean.MessageEvent;
import com.bwie.jingdong.R;

import org.greenrobot.eventbus.EventBus;

public class RegisterActivity extends AppCompatActivity implements IRegisterView, View.OnClickListener {

    /**
     * 请输入账号
     */
    private EditText mUserName;
    /**
     * 请输入密码
     */
    private EditText mUserPwd;
    /**
     * 注册
     */
    private Button mRegisterBtn;

    private IRegisterPresenter presenter;
    private ImageView mZcfh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        initView();
        presenter = new RegisterPresenter(this);
    }

    private void initView() {
        mUserName = (EditText) findViewById(R.id.user_name);
        mUserPwd = (EditText) findViewById(R.id.user_pwd);
        mRegisterBtn = (Button) findViewById(R.id.register_btn);
        mRegisterBtn.setOnClickListener(this);
        mZcfh = (ImageView) findViewById(R.id.zcfh);
        mZcfh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:
                String name = mUserName.getText().toString();
                String pwd = mUserPwd.getText().toString();
                presenter.setInfoData(name, pwd);
                break;
            case R.id.zcfh:
                finish();
                break;
        }
    }

    @Override
    public void phoneError() {
        Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdError() {
        Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(String name) {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new MessageEvent(name));
        //銷毀
        finish();
    }

    @Override
    public void loginError() {
        Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
    }
}
