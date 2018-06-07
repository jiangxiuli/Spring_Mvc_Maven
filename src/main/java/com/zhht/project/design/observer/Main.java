package com.zhht.project.design.observer;

public class Main {

    public static void main(String[] args) {
        WechatServer wechatServer = new WechatServer();

        User user1 = new User();
        user1.name = "jxl";

        User user2 = new User();
        user2.name = "zjj";

        User user3 = new User();
        user3.name = "zsh";

        // 订阅
        wechatServer.addOberserver(user1);
        wechatServer.addOberserver(user2);
        wechatServer.addOberserver(user3);

        wechatServer.setMessage("每个人要对自己有要求");

    }

}
