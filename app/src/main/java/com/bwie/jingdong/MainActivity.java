package com.bwie.jingdong;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.bwie.fragment.Fragment_Card;
import com.bwie.fragment.Fragment_Class;
import com.bwie.fragment.Fragment_FaXian;
import com.bwie.fragment.Fragment_ShouYe;
import com.bwie.fragment.Fragment_wode;

public class MainActivity extends AppCompatActivity{

    private RadioGroup mRadioGroup;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //获取Fragment管理者
        manager=getSupportFragmentManager();
        //默认加载第一个页面
        manager.beginTransaction().replace(R.id.frameLayout, new Fragment_ShouYe()).commit();

        initView();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.home:
                        getFragment(new Fragment_ShouYe());
                        break;
                    case R.id.fclass:
                         getFragment(new Fragment_Class());
                        break;
                    case R.id.faxian:
                        getFragment(new Fragment_FaXian());
                        break;
                    case R.id.card:
                        getFragment(new Fragment_Card());
                        break;
                    case R.id.my:
                        getFragment(new Fragment_wode());
                        break;
                }
            }
        });
    }

    private void getFragment(Fragment fragment){
        manager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
    }
}
