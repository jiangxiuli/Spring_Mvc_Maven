package com.zhht.project.test.producerconsumer.multiple;


public class Mutil_Producer_Consumer {
	public static void main(String[] args)   
    {  
		KaoyaResourceByLock r = new KaoyaResourceByLock();  
        Mutil_Producer pro = new Mutil_Producer(r);  
        Mutil_Consumer con = new Mutil_Consumer(r);  
        //生产者线程  
        Thread t0 = new Thread(pro);  
        Thread t1 = new Thread(pro);  
        //消费者线程  
        Thread t2 = new Thread(con);  
        Thread t3 = new Thread(con);  
        //启动线程  
        t0.start();  
        t1.start();  
        t2.start();  
        t3.start();  
    }
}
