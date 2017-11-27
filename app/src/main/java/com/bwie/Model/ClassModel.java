package com.bwie.Model;

import android.os.Handler;

import com.bwie.Presenter.IClassPresenter;
import com.bwie.api.Api;
import com.bwie.bean.OneClass;
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

public class ClassModel implements IClassModel{

    private Handler handler=new Handler();

    @Override
    public void oneClass(final IClassPresenter iClassPresenter) {

        OkHttp3Utils.doGet(Api.ONECLASS, new Callback() {
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
                            OneClass oneClass = gson.fromJson(string, OneClass.class);
                            if(oneClass==null){
                                return;
                            }
                            List<OneClass.DataBean> data = oneClass.getData();
                            iClassPresenter.oneClass(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });


    }
}
