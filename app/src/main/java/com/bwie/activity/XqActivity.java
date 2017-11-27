package com.bwie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.Presenter.ISouPresenter;
import com.bwie.Presenter.SouPresenter;
import com.bwie.View.IAddCardView;
import com.bwie.bean.SouShow;
import com.bwie.jingdong.R;
import com.bwie.mylibrary.SharedUtil;
import com.bwie.utils.CartPopupWindow;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bwie.jingdong.R.id.webview;

public class XqActivity extends AppCompatActivity implements IAddCardView{

    @Bind(R.id.share)
    TextView mShare;
    @Bind(R.id.banner_xq)
    Banner mBannerXq;
    @Bind(R.id.title_xq)
    TextView mTitleXq;
    @Bind(R.id.price_xq)
    TextView mPriceXq;
    @Bind(webview)
    WebView mWebview;
    @Bind(R.id.scrollView)
    ScrollView mScrollView;
    @Bind(R.id.card)
    Button mCard;
    @Bind(R.id.add)
    Button mAdd;
    @Bind(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    private ISouPresenter iSouPresenter;
    private SouShow.DataBean souShow;
    String string = "https://item.jd.com/5025518.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        iSouPresenter=new SouPresenter(this);
        data();
    }

    //获取数据
    private void data() {
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        souShow= (SouShow.DataBean) data.getSerializable("data");
        String[] split = souShow.getImages().split("\\|");
        setBanner(split);

        mWebview.loadUrl(string);
        WebSettings settings = mWebview.getSettings();
        mWebview.setWebViewClient(new WebViewClient());
        double price = souShow.getPrice();
        String title = souShow.getTitle();
        Log.i("xxx", title);
        mTitleXq.setText(title);
        mPriceXq.setText("¥: " + price);
    }

    private void setBanner(String[] split) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }

        if (list == null) {
            Toast.makeText(this,"图片异常",Toast.LENGTH_SHORT).show();
            return;
        }
        //设置自动轮播，通常情况下为true
        mBannerXq.isAutoPlay(true);
        //图片集合
        mBannerXq.setImages(list);
        //间隔
        mBannerXq.setDelayTime(3000);
        //样式
        mBannerXq.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
    }

    @OnClick({R.id.share, R.id.add})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share:
                break;
            case R.id.add:
                CartPopupWindow window = new CartPopupWindow(this, souShow);
                //用来获取添加商品的数量
                window.setIshop(new CartPopupWindow.Ishop() {
                    @Override
                    public void num(int num) {
                        addCard(num);
                    }
                });
                window.showAtLocation(this.findViewById(R.id.relativeLayout), Gravity.BOTTOM, 0, 0);
                break;
        }
    }
    private void addCard(int num) {
        //又要请求服务器  来添加商品数量
        SharedUtil instances = SharedUtil.getInstances();
        int uid = (int) instances.getValueByKey(this, "uid", 0);
        iSouPresenter.addCard(uid, souShow.getPid(), souShow.getSellerid());

    }
}
