package com.zhht.project.design.singleton;

public class LazySingleton {
    private static LazySingleton lazySingleton;

    private LazySingleton() {
        super();
    }

    public static LazySingleton getLazySingleton() {
        if (lazySingleton == null) {
            synchronized (LazySingleton.class) {
                if (lazySingleton == null) {
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}
