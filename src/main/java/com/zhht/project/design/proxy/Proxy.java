package com.zhht.project.design.proxy;

public class Proxy implements GiveGift {
    private Man man;

    Proxy() {

    }

    public Proxy(Girl girl) {
        this.man = new Man(girl);
    }

    @Override
    public void songWawa() {
        man.songWawa();
    }

    public static void main(String[] args) {
        Proxy proxy = new Proxy(new Girl("小丽"));
        proxy.songWawa();
    }
}
