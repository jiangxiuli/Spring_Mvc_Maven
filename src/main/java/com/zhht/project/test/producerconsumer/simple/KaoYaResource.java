package com.zhht.project.test.producerconsumer.simple;

public class KaoYaResource {
	private String name;
	private int count = 1;// 初始烤鸭数量
	private boolean flag = false;// 需要线程等待的标志，有烤鸭

	public synchronized void product(String name) throws InterruptedException {
		while (flag) {// 有烤鸭
			this.wait();
		}
		this.name = name + count;// 设置烤鸭的名称
		count++;
		System.out.println(Thread.currentThread().getName() + "...生产者..." + this.name);
		flag = true;// 有烤鸭
		notifyAll();// 通知消费线程可以消费了
	}

	public synchronized void consume() throws InterruptedException {
		while (!flag) {// 没烤鸭
			this.wait();
		}
		System.out.println(Thread.currentThread().getName() + "...消费者..." + this.name);// 消费烤鸭
		flag = false;// 没烤鸭
		notifyAll();// 通知生产者可以生产了
	}
}
