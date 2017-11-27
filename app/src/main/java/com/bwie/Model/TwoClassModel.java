package com.bwie.Model;

import android.os.Handler;

import com.bwie.Presenter.ITwoClassPresenter;
import com.bwie.api.Api;
import com.bwie.bean.TwoClass;
import com.bwie.mylibrary.OkHttp3Utils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ${单巨廷} on 2017/11/5.
 */

public class TwoClassModel implements ITwoClassModel{

    private Handler handler=new Handler();

    @Override
    public void twoclass(int cid, final ITwoClassPresenter presenter) {

        OkHttp3Utils.doGet(Api.TWOCLASS + "?cid=" + cid, new Callback() {
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
                            TwoClass twoClass = gson.fromJson(string, TwoClass.class);
                            List<TwoClass.DataBean> twoClassData = twoClass.getData();
                            presenter.twoClass(twoClassData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }
}
