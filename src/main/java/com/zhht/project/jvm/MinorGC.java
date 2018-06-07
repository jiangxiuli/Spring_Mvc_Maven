package com.zhht.project.jvm;

public class MinorGC {

    private static final int _1MB = 1024 * 1024;

    // -Xms20M -Xms20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDateStamps
    // -XX:+PrintGCDetails -XX:PermSize=10M -XX:MaxPermSize=10m
    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7, allocation8;
        System.out.println("==============1");
        allocation1 = new byte[1 * _1MB];
        
        
        System.out.println("==============2");
        allocation2 = new byte[3 * _1MB];
        
       
        System.out.println("==============3");       
        allocation3 = new byte[4 * _1MB];
        
        
        System.out.println("==============4");
        allocation4 = new byte[1/4 * _1MB];
        
        
        System.out.println("==============5");
        allocation5 = new byte[1/4 * _1MB];
        
        
        System.out.println("==============6");
        allocation6 = new byte[1 * _1MB];
        
        
        System.out.println("==============7");
        allocation7 = new byte[1 * _1MB];
        
        
        System.out.println("==============8");
        allocation8 = new byte[1 * _1MB];
        
        System.gc();
    }
}