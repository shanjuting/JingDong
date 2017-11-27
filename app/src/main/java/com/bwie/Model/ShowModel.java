package com.bwie.Model;

import android.os.Handler;

import com.bwie.Presenter.IShowPresenter;
import com.bwie.api.Api;
import com.bwie.bean.ShowPage;
import com.bwie.mylibrary.OkHttp3Utils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ${单巨廷} on 2017/11/3.
 */

public class ShowModel implements IShowModel{

    private Handler handler=new Handler();

    @Override
    public void getData(final IShowPresenter iShowPresenter) {

        OkHttp3Utils.doGet(Api.SHOW, new Callback() {
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
                                JSONObject jsonObject = new JSONObject(string);
                                if(jsonObject.opt("code").equals("0")){
                                    Gson gson = new Gson();
                                    ShowPage showPage = gson.fromJson(string, ShowPage.class);
                                    List<ShowPage.DataBean> data = showPage.getData();
                                    ShowPage.MiaoshaBean miaosha = showPage.getMiaosha();
                                    ShowPage.TuijianBean tuijian = showPage.getTuijian();
                                    iShowPresenter.getData(data,miaosha,tuijian);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                });

            }
        });

    }
}
