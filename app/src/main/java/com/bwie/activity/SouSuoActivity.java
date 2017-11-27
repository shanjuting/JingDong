package com.bwie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.bwie.Presenter.ISouPresenter;
import com.bwie.Presenter.SouPresenter;
import com.bwie.View.ISousou;
import com.bwie.adapter.IListener;
import com.bwie.adapter.SouAdapter;
import com.bwie.bean.SouShow;
import com.bwie.jingdong.R;

import java.util.List;

import static com.bwie.jingdong.R.id.et;
import static com.bwie.jingdong.R.id.recyclerView;
import static com.bwie.jingdong.R.id.soso;
import static com.bwie.jingdong.R.id.swipyRefreshLayout;

public class SouSuoActivity extends AppCompatActivity implements View.OnClickListener, ISousou, IListener {


    /**
     * 多快,好省,上京东
     */
    private EditText mEt;
    private CheckBox mCheck;
    /**
     * 搜索
     */
    private CheckBox mSoso;
    private LinearLayout mHeadLayout;
    private RecyclerView mRecyclerView;
    private SwipyRefreshLayout mSwipyRefreshLayout;
    private ISouPresenter iSouPresenter;

    int page = 1;    //接口数据 第一页
    private SouAdapter adapter;
    private String so;
    Handler handler = new Handler();
    List<SouShow.DataBean> data;
    private ImageView mDenglufanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        getSupportActionBar().hide();
        initView();
        iSouPresenter = new SouPresenter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //刷新
        mSwipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        mEt.setOnClickListener(this);

        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                page = 1;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iSouPresenter.infoData(so, page);
                        mSwipyRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }

            @Override
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        iSouPresenter.infoData(so, page);
                        mSwipyRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        mSoso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //获取数据   将数据交到  p->m
                so = mEt.getText().toString();
                iSouPresenter.infoData(so, page);
            }
        });


        mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheck.isChecked()) {
                    mRecyclerView.setLayoutManager(new GridLayoutManager(SouSuoActivity.this, 2));
                    return;
                }
                //线性布局
                mRecyclerView.setLayoutManager(new LinearLayoutManager(SouSuoActivity.this));
            }
        });


    }


    private void initView() {
        mEt = (EditText) findViewById(et);
        mEt.setOnClickListener(this);
        mCheck = (CheckBox) findViewById(R.id.check);
        mSoso = (CheckBox) findViewById(soso);
        mHeadLayout = (LinearLayout) findViewById(R.id.head_layout);
        mRecyclerView = (RecyclerView) findViewById(recyclerView);
        mSwipyRefreshLayout = (SwipyRefreshLayout) findViewById(swipyRefreshLayout);
        mCheck.setOnClickListener(this);
        mSoso.setOnClickListener(this);
        mHeadLayout.setOnClickListener(this);
        mDenglufanhui = (ImageView) findViewById(R.id.denglufanhui);
        mDenglufanhui.setOnClickListener(this);
        mEt = (EditText) findViewById(R.id.et);
        mSoso = (CheckBox) findViewById(R.id.soso);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSwipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.swipyRefreshLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et:
                socheck();
                break;

            case R.id.denglufanhui:
                finish();
                break;
        }
    }

    @Override
    public void infodata(List<SouShow.DataBean> list) {
        this.data = list;
        mSoso.setVisibility(View.GONE);
        mCheck.setVisibility(View.VISIBLE);
        //适配器
        setapater(data);
    }

    private void setapater(List<SouShow.DataBean> data) {
        if (adapter == null) {
            adapter = new SouAdapter(this, data, this);
            mRecyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setListener(int position) {
        //对应的Item 数据
        SouShow.DataBean dataBean = data.get(position);
        //获取点击数据或要跳转到详情页面
        Intent intent = new Intent(this, XqActivity.class);
        intent.putExtra("data", dataBean);
        startActivity(intent);
    }

    //切换  搜索  和  布局
    private void socheck() {
        mSoso.setVisibility(View.VISIBLE);
        mCheck.setVisibility(View.GONE);
    }
}
