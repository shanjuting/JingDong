package com.bwie.utils;

/**
 * Created by ${单巨廷} on 2017/11/16.
 */

public class ParentBean {
    public String title;        //一级列表的标题
    public boolean isCheck;     //一级列表的CheckBox
    public boolean ziCheck;     //一级列表的编辑开关
    public ParentBean(String title,boolean isCheck,boolean ziCheck)
    {
        this.ziCheck=ziCheck;
        this.title=title;
        this.isCheck=isCheck;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isZiCheck() {
        return ziCheck;
    }

    public void setZiCheck(boolean ziCheck) {
        this.ziCheck = ziCheck;
    }
}
