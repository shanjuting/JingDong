package com.bwie.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

import com.bwie.api.Api;
import com.bwie.mylibrary.OkHttp3Utils;
import com.bwie.mylibrary.SharedUtil;
import com.bwie.utils.Files;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by ${单巨廷} on 2017/11/2.
 */

public class PicModel implements IPicModel {

    private Handler handler=new Handler();

    @Override
    public void setInfoPic(Bitmap bitmap, final Context context) {

        File file = Files.getFile(bitmap);
        final SharedUtil instances = SharedUtil.getInstances();
        final int uid = (int) instances.getValueByKey(context, "uid", 0);

        OkHttp3Utils.loadFile(Api.PIC+"?uid="+uid, file, "temp.jpg",new Callback(){

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
                                       Log.i("string",string);
                                       RegisterModel registerModel = new RegisterModel();
                                       registerModel.InfoUser(uid,context,instances);
                                   } catch (IOException e) {
                                       e.printStackTrace();
                                   }
                               }
                           });
            }
        });
    }
}
