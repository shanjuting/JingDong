package com.bwie.Model;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.bwie.Presenter.IRegisterPresenter;
import com.bwie.api.Api;
import com.bwie.mylibrary.OkHttp3Utils;
import com.bwie.mylibrary.SharedUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ${单巨廷} on 2017/11/1.
 */

public class RegisterModel implements IRegisterModel{

    private Handler handler=new Handler();

    //注册
    @Override
    public void setInfoData(String name, String pwd, final IRegisterPresenter iRegisterPresenter) {
              if(TextUtils.isEmpty(name)){
                  iRegisterPresenter.phoneError();
                  return;
              }
              if (TextUtils.isEmpty(pwd)){
                  iRegisterPresenter.pwdError();
                  return;
              }
        Map<String,String> map=new HashMap<>();
        map.put("mobile",name);
        map.put("password",pwd);

        OkHttp3Utils.doPost(Api.REGISTER, map, new Callback() {
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
                            JSONObject object = new JSONObject(string);
                            if(object.opt("code").equals("0")){
                                JSONObject data = object.optJSONObject("data");
                                String mobile = (String) data.opt("mobile");
                                iRegisterPresenter.loginSuccess(mobile);
                                return;
                            }
                            iRegisterPresenter.loginError();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


    }

    //登录
    @Override
    public void setInfoLogin(String name, String pwd, final IRegisterPresenter iRegisterPresenter, final Context context) {
        if(TextUtils.isEmpty(name)){
            iRegisterPresenter.phoneError();
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            iRegisterPresenter.pwdError();
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("mobile",name);
        map.put("password",pwd);

        OkHttp3Utils.doPost(Api.LOGIN, map, new Callback() {
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
                                    JSONObject object = new JSONObject(string);
                                    if(object.opt("code").equals("0")){
                                        JSONObject object1 = object.optJSONObject("data");
                                        String username = (String) object1.opt("username");
                                        int uid= (int) object1.opt("uid");

                                        SharedUtil instances = SharedUtil.getInstances();
                                        instances.saveDatad(context,"uid",uid);
                                        instances.saveDatad(context,"username",username);
                                        instances.saveDatad(context,"login_success",true);
                                        InfoUser(uid,context,instances);
                                        iRegisterPresenter.loginSuccess("");
                                    }
                                    iRegisterPresenter.loginError();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });
    }
    //获取头像
    public void InfoUser(int uid, final Context context, final SharedUtil sharedUtil){

        OkHttp3Utils.doGet(Api.Info + "?uid=" + uid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject object = new JSONObject(string);
                    if (object.opt("code").equals("0")){
                        //获取信息成功
                        JSONObject data = object.optJSONObject("data");
                        String icon = (String) data.opt("lll");
                        sharedUtil.saveDatad(context,"lll",icon);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
