package com.bwie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.bwie.jingdong.R;

public class NPActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 添加新地址
     */
    private Button mTjnewad;
    private ImageView mDddback;
    private ListView mLv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_np);
        initView();

    }

    private void initView() {
        mTjnewad = (Button) findViewById(R.id.tjnewad);
        mTjnewad.setOnClickListener(this);
        mDddback = (ImageView) findViewById(R.id.dddback);
        mDddback.setOnClickListener(this);
        mLv = (ListView) findViewById(R.id.lv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tjnewad:
                startActivity(new Intent(NPActivity.this, AddAddressActivity.class));
                break;
            case R.id.dddback:
                finish();
                break;
        }
    }
}
