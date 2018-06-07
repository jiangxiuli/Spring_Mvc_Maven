package com.zhht.project.design.singleton;

public class HungrySingleton {
    private static HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {
        super();
    }

    public static HungrySingleton getHungrySingleton() {
        return hungrySingleton;
    }

}
