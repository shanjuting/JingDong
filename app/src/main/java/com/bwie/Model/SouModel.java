package com.bwie.Model;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.bwie.Presenter.ISouPresenter;
import com.bwie.api.Api;
import com.bwie.bean.ShopCardBean;
import com.bwie.bean.SouShow;
import com.bwie.mylibrary.OkHttp3Utils;
import com.bwie.mylibrary.SharedUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ${单巨廷} on 2017/11/3.
 */

public class SouModel implements ISouModel{

    private List<SouShow.DataBean> dataBeen;

    private Handler handler=new Handler();

    @Override
    public void infoData(String name, final int page, final ISouPresenter presenter) {
        String path = "https://www.zhaoapi.cn/product/searchProducts?keywords=" + name + "&page=" + page+"&source=android";
        OkHttp3Utils.doGet(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String string = response.body().string();
                            Gson gson = new Gson();
                            SouShow souShow = gson.fromJson(string, SouShow.class);
                            List<SouShow.DataBean> data = souShow.getData();
                            if(page==1){
                                dataBeen = new ArrayList<SouShow.DataBean>();
                            }
                            for (int i = 0; i < data.size(); i++) {
                                dataBeen.add(data.get(i));
                            }
                            presenter.infoData(dataBeen);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void addCard(int uid, int pid, int sellerid, ISouPresenter presenter) {
//请求网络   加入购物车
        String s=Api.ADDCARD + "?uid=" + uid + "&pid=" + pid + "&=sellerid" + sellerid;
        Log.i("tag",s);
        OkHttp3Utils.doGet(s, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("cardOK",response.body().string());
            }
        });
    }

    @Override
    public void selectCard(Context context, final ISouPresenter presenter) {
        SharedUtil instances = SharedUtil.getInstances();
        int uid = (int) instances.getValueByKey(context,"uid",0);
        OkHttp3Utils.doGet(Api.SELECT + "?uid=" + uid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String string = response.body().string();
                            Log.i("stringggg",string);
                            Gson gson = new Gson();
                            ShopCardBean shopCardBean = gson.fromJson(string, ShopCardBean.class);
                            Log.i("ccc",shopCardBean.toString());
                            /**
                             * 将数据给购物车
                             */
                            presenter.getCardData(shopCardBean);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
