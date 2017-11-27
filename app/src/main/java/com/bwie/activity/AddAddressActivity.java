package com.bwie.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bwie.bean.AddAddress;
import com.bwie.jingdong.R;
import com.bwie.mylibrary.OkHttp3Utils;
import com.bwie.mylibrary.SharedUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入收货人姓名
     */
    private EditText mNameren;
    /**
     * 请输入收货人电话
     */
    private EditText mNamephone;
    /**
     * 请输入收货新地址
     */
    private EditText mNewAddress;
    /**
     * 添加
     */
    private Button mAddyonghu;
    private ImageView mDdddback;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String obj = (String) msg.obj;
                    if(obj.equals("0")){
                        finish();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initView();
    }

    private void initView() {
        mNameren = (EditText) findViewById(R.id.nameren);
        mNamephone = (EditText) findViewById(R.id.namephone);
        mNewAddress = (EditText) findViewById(R.id.newAddress);
        mAddyonghu = (Button) findViewById(R.id.addyonghu);
        mAddyonghu.setOnClickListener(this);
        mDdddback = (ImageView) findViewById(R.id.ddddback);
        mDdddback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addyonghu:
                String s = mNameren.getText().toString();
                String s1 = mNamephone.getText().toString();
                String s2 = mNewAddress.getText().toString();
                int uid = (int) SharedUtil.getInstances().getValueByKey(this, "uid",1);
                Map<String,String> map=new HashMap<>();
                map.put("uid",uid+"");
                map.put("name",s);
                map.put("mobile",s1);
                map.put("addr",s2);
                Log.i("uid",map.get("uid")+map.get("name")+map.get("mobile")+map.get("addr"));
                OkHttp3Utils.doPost("http://120.27.23.105/user/addAddr", map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                   Log.i("exception",e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String string = null;
                                try {
                                    string = response.body().string();
                                    Log.i("xxx",string);
                                    Gson gson = new Gson();
                                    AddAddress addAddress = gson.fromJson(string, AddAddress.class);
                                    Message message = new Message();
                                    message.what=0;
                                    message.obj=addAddress.getCode();
                                    handler.sendMessage(message);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                    }
                });

                break;
            case R.id.ddddback:
                finish();
                break;
        }
    }
}
