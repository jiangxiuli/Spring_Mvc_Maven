package com.zhht.project.design.observer;

public class User implements Observer {

    public String name;

    @Override
    public void update(String message) {
        System.out.println(name + "接收到了消息：" + message);

    }

}
