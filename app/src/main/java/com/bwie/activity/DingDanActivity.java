package com.bwie.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.adapter.MyAdater;
import com.bwie.alipay.AliPayActivity;
import com.bwie.alipay2.WeChatActivity;
import com.bwie.bean.ShowAddress;
import com.bwie.jingdong.R;
import com.bwie.mylibrary.OkHttp3Utils;
import com.bwie.mylibrary.SharedUtil;
import com.bwie.utils.ChildBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.bwie.jingdong.R.id.dingdan;
import static com.bwie.jingdong.R.id.sumprice;

public class DingDanActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(dingdan)
    ListView mDingdan;
    @Bind(sumprice)
    TextView mSumprice;
    @Bind(R.id.btn)
    Button mBtn;
    @Bind(R.id.relativeLayout2)
    RelativeLayout mRelativeLayout2;
    private List<ChildBean> card_02s1;
    private ImageView mDdback;
    /**
     * 收货人姓名:单巨廷 手机号:15201283435
     */
    private TextView mShrname;
    /**
     * 收获地址
     */
    private TextView mShaddress;
    private LinearLayout mXiugaidizhi;
    /**
     * 手机号:15201283435
     */
    private TextView mShphone;

    private String path="http://120.27.23.105/user/getAddrs";

    private Handler handler=new Handler();

    @Override
    protected void onResume() {
        super.onResume();


            int uid = (int) SharedUtil.getInstances().getValueByKey(this, "uid", 1);
            //http://120.27.23.105/user/getAddrs?uid=71
            Map<String,String> map=new HashMap<>();
            map.put("uid",uid+"");
            OkHttp3Utils.doPost(path, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String string = null;
                            try {
                                string = response.body().string();
                                Log.i("xxxx",string);
                                Gson gson = new Gson();
                                ShowAddress showAddress = gson.fromJson(string, ShowAddress.class);
                                int size = showAddress.getData().size();
                                mShrname.setText("收货人姓名:"+showAddress.getData().get(size-1).getName());
                                mShphone.setText("手机号:"+showAddress.getData().get(size-1).getMobile()+"");
                                mShaddress.setText("收货地址:"+showAddress.getData().get(size-1).getAddr());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                }
            });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);
        initView();
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        //获取数据
        Intent intent1=getIntent();

        Bundle bundle = intent1.getBundleExtra("bundle");
        card_02s1 = (List<ChildBean>) bundle.getSerializable("data");
        double sum = 0;
        for (int i = 0; i < card_02s1.size(); i++) {
            double v = card_02s1.get(i).getSaleNum() * card_02s1.get(i).getPrice();
            sum = sum + v;
        }
        Log.i("sum", sum + "");
        mSumprice.setText("¥:" + sum);
        MyAdater myAdater = new MyAdater(card_02s1, this);
        mDingdan.setAdapter(myAdater);


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建数据
                final String[] items = new String[]{"微信支付", "支付宝支付"};
                // 创建对话框构建器
                AlertDialog.Builder builder = new AlertDialog.Builder(DingDanActivity.this);
                // 设置参数
                builder.setIcon(R.mipmap.icon).setTitle("提示:请选择您的支付方式$:")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (items[which].equals("支付宝支付")) {
                                    startActivity(new Intent(DingDanActivity.this, AliPayActivity.class));
                                } else if (items[which].equals("微信支付")) {
                                    startActivity(new Intent(DingDanActivity.this, WeChatActivity.class));
                                }
                            }
                        });
                builder.create().show();
            }
        });
    }

    private void initView() {
        mDdback = (ImageView) findViewById(R.id.ddback);
        mDdback.setOnClickListener(this);
        mShrname = (TextView) findViewById(R.id.shrname);
        mShaddress = (TextView) findViewById(R.id.shaddress);
        mXiugaidizhi = (LinearLayout) findViewById(R.id.xiugaidizhi);
        mXiugaidizhi.setOnClickListener(this);
        mRelativeLayout2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
        mShphone = (TextView) findViewById(R.id.shphone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ddback:
                finish();
                break;
            case R.id.xiugaidizhi:
                startActivity(new Intent(DingDanActivity.this, NPActivity.class));
                break;
        }
    }
}
