package com.zhht.project.design.proxy;

public class Man implements GiveGift {
    private Girl girl;

    @Override
    public void songWawa() {
        System.out.println("男孩送女孩" + girl.getName() + "娃娃");
    }

    public Man(Girl girl) {
        super();
        this.girl = girl;
    }
}
