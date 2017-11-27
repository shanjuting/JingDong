package com.bwie.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.Presenter.IPicPresenter;
import com.bwie.Presenter.PicPresenter;
import com.bwie.View.IPicView;
import com.bwie.activity.LoginActivity;
import com.bwie.jingdong.R;
import com.bwie.mylibrary.SharedUtil;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import static com.bwie.jingdong.R.id.login;

/**
 * Created by ${单巨廷} on 2017/10/31.
 */

public class Fragment_wode extends Fragment implements View.OnClickListener,IPicView{
    private View view;
    private ImageView mHeadIv;
    /**
     * 登陆
     */
    private TextView mLogin;
    private RelativeLayout mLayoutLogin;
    private SharedUtil instances;
    private boolean login_success;
    private IPicPresenter presenter;
    private boolean flag;
    private List<String> list;
    private Banner banner;
    public final static String Ban[] = new String[]{
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3347886770,2607408799&fm=27&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3046402704,87002965&fm=11&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2752283185,2551775464&fm=27&gp=0.jpg"
            };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment5, container, false);
        initView(view);
        banner = (Banner) view.findViewById(R.id.banner);
        list = new ArrayList<>();
        for (int i = 0; i < Ban.length; i++){
            list.add(Ban[i]);
        }

//无限轮播
//设置加载样式
        banner.setIndicatorGravity(Banner.CENTER);
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
//是否自动轮播
        banner.isAutoPlay(true);
//设置轮播的时间间隔
        banner.setDelayTime(3000);
//设置加载的图片，解析出来的url
        banner.setImages(list);
        return view;
    }

    private void initView(View view) {
        presenter = new PicPresenter(this);
        instances = SharedUtil.getInstances();

        login_success = (boolean) instances.getValueByKey(getActivity(), "login_success", false);
        mHeadIv = (ImageView) view.findViewById(R.id.head_iv);
        mHeadIv.setOnClickListener(this);
        mLogin = (TextView) view.findViewById(login);
        mLogin.setOnClickListener(this);
        mLayoutLogin = (RelativeLayout) view.findViewById(R.id.layout_login);
        mLayoutLogin.setOnClickListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        instances = SharedUtil.getInstances();
        login_success = (boolean) instances.getValueByKey(getActivity(), "login_success", false);
        if (login_success) {
            String username = (String) instances.getValueByKey(getActivity(), "username", "");
            mLogin.setText(username);
            if (flag) {
                flag = false;
                return;
            }
            String icon = (String) instances.getValueByKey(getActivity(), "lll", "");
            if (icon != null) {
     //          Picasso.with(getActivity()).load(icon).placeholder(R.mipmap.gouwuche1).into(mHeadIv);
                Log.i("xxx", icon);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_iv:
                //登陆时可以修改头像
                if (!login_success) {
                    Toast.makeText(getActivity(),"请优先登录",Toast.LENGTH_SHORT).show();
                    return;
                }
                //打开相册
                open();
                break;
            case login:
                if (!login_success) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.layout_login:

                break;
        }
    }
    //打开相册
    private void open() {
        // 调取系统的相册,Intent.ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        //设置取回的数据类型
        intent.setType("image/*");
        //利用回传
        startActivityForResult(intent, 4000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2.设置图片
        if (requestCode == 3000) {
            Bitmap bitmap = data.getParcelableExtra("data");
            mHeadIv.setImageBitmap(bitmap);
            flag = true;
            //將BitMap 传到   p  交互
            presenter.setInfoPic(bitmap, getActivity());
        }
        // 3.调取完系统的相册后 进行裁剪
        if (requestCode == 4000) {
            // 得到相册的图片的路径,直接用这个getData;
            if (data == null) {
                return;
            }
            Uri uri = data.getData();
            // 3.调取系统的裁剪
            Intent it = new Intent("com.android.camera.action.CROP");
            // 拿到图片 type 图片的类型
            it.setDataAndType(uri, "image/*");

            // 是否支持裁剪
            it.putExtra("crop", true);
            // 设置宽高比
            it.putExtra("aspectX", 1);
            it.putExtra("aspectY", 1);
            // 设置图片输出的大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            // 是否支持人脸识别
            it.putExtra("onFaceDetection", false);
            it.putExtra("return-data", true);

            startActivityForResult(it, 3000);
        }
    }
}
