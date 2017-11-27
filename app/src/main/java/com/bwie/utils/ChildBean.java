package com.bwie.utils;

import java.io.Serializable;

/**
 * Created by ${单巨廷} on 2017/11/16.
 */

public class ChildBean implements Serializable{
    public String content;      //二级列表的内容
    public boolean isCheck;     //二级列表的CheckBox
    public boolean viewChange;  //二级列表是否显示编辑界面
    public int saleNum;         //二级列表的数量
    public double price;           //二级列表的价格
    public String imageUrl;

    public ChildBean(String content, boolean isCheck, boolean viewChange, int saleNum, double price, String imageUrl) {
        this.content = content;
        this.isCheck = isCheck;
        this.viewChange = viewChange;
        this.saleNum = saleNum;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isViewChange() {
        return viewChange;
    }

    public void setViewChange(boolean viewChange) {
        this.viewChange = viewChange;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
