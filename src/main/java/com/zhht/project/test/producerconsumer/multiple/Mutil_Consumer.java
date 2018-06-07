package com.zhht.project.test.producerconsumer.multiple;

public class Mutil_Consumer implements Runnable {
	private KaoyaResourceByLock r;

	Mutil_Consumer(KaoyaResourceByLock r) {
		this.r = r;
	}

	public void run() {
		while (true) {
			r.consume();
		}
	}
}
