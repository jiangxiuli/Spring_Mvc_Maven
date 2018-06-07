package com.zhht.project.design.singleton;

public class MainTest {
    public static void main(String[] args) {
        HungrySingleton hungrySingleton1 = HungrySingleton.getHungrySingleton();
        HungrySingleton hungrySingleton2 = HungrySingleton.getHungrySingleton();
        
        System.out.println(hungrySingleton1 == hungrySingleton2);
        
        
        LazySingleton lazySingleton1 = LazySingleton.getLazySingleton();
        LazySingleton lazySingleton2 = LazySingleton.getLazySingleton();
        
        System.out.println(lazySingleton1 == lazySingleton2);
        System.out.println();
    }
}
