package com.zhht.project.test;

public class TestThread implements Runnable {

	private int count;

	public static void main(String[] args) throws InterruptedException {
		TestThread testThread = new TestThread();
		Thread[] thread = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(testThread);
		}

		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}

		TestThread testThread1 = new TestThread();
		Thread[] thread1 = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread1[i] = new Thread(testThread1);
		}
		for (int i = 0; i < 10; i++) {
			thread1[i].start();
		}
	}

	@Override
	public void run() {
		add();
	}

	public synchronized void add() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count++;
		System.out.println("" + count);
	}
}
