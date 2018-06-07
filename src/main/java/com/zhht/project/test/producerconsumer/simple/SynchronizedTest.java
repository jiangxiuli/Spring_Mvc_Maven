package com.zhht.project.test.producerconsumer.simple;

import org.junit.Test;

import com.zhht.project.junit.BaseJunit;

public class SynchronizedTest extends BaseJunit implements Runnable{
    public synchronized void method1(){
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public synchronized void method2(){
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public static void main(String[] args) {
        final SynchronizedTest test = new SynchronizedTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method2();
            }
        }).start();
    }
    
    @Test
    public void test () {
    	SynchronizedTest test = new SynchronizedTest();
    	Thread thread = new Thread (test);
    	thread.start();
    }

	@Override
	public void run() {
//		SynchronizedTest test = new SynchronizedTest();
//		test.
		method1();
//		test.
		method2();
	}
}