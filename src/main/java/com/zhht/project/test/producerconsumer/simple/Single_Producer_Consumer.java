package com.zhht.project.test.producerconsumer.simple;

public class Single_Producer_Consumer {

	public static void main(String[] args) {
		KaoYaResource r = new KaoYaResource();
		Producer producer = new Producer(r);
		Consumer consumer = new Consumer(r);
		
		Thread tproducer = new Thread(producer);
		Thread tconsumer = new Thread(consumer);
		
		tproducer.start();
		tconsumer.start();
	}
}
