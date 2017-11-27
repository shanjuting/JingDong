package com.bwie.bean;

/**
 * Created by ${单巨廷} on 2017/11/2.
 */

public class MessageEvent {
    private String name;
    private boolean aBoolean;

    public MessageEvent(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }


    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public MessageEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "name='" + name + '\'' +
                '}';
    }
}
