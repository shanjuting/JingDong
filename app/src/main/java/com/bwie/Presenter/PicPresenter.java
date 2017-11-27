package com.bwie.Presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.bwie.Model.IPicModel;
import com.bwie.Model.PicModel;
import com.bwie.View.IPicView;

/**
 * Created by ${单巨廷} on 2017/11/2.
 */

public class PicPresenter implements IPicPresenter{
    private IPicView iPicView;

    private IPicModel iPicModel;
    public PicPresenter(IPicView iPicView) {
        this.iPicView = iPicView;
        iPicModel = new PicModel();
    }

    @Override
    public void setInfoPic(Bitmap bitmap, Context context) {
        iPicModel.setInfoPic(bitmap,context);
    }
}
