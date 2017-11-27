package com.bwie.bean;

/**
 * Created by ${单巨廷} on 2017/11/16.
 */

public class BianjiBean {

    private boolean bb;

    public BianjiBean(boolean bb) {
        this.bb = bb;
    }

    public BianjiBean() {
    }

    public boolean isBb() {
        return bb;
    }

    public void setBb(boolean bb) {
        this.bb = bb;
    }

    @Override
    public String toString() {
        return "BianjiBean{" +
                "bb=" + bb +
                '}';
    }
}
