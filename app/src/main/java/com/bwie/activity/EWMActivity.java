package com.bwie.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bwie.jingdong.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class EWMActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 点击
     */
    private Button mEr;
    private Button mSc;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ewm);
        getSupportActionBar().hide();
        initView();
    }

    private void initView() {
        mEr = (Button) findViewById(R.id.er);
        mEr.setOnClickListener(this);
        mSc = (Button) findViewById(R.id.sc);
        mSc.setOnClickListener(this);
        mImage = (ImageView) findViewById(R.id.image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.er:
                Intent openCameraIntent = new Intent(EWMActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;
            case R.id.sc:
                String contentString = "11111";
                if (!contentString.equals("")) {
                    //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
                    Bitmap qrCodeBitmap = EncodingUtils.createQRCode(contentString, 350, 350,
                            BitmapFactory.decodeResource(getResources(), R.mipmap.icon));
                    mImage.setImageBitmap(qrCodeBitmap);
                }

                break;
        }
    }
}
