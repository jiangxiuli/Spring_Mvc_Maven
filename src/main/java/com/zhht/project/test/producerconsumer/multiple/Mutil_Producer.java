package com.zhht.project.test.producerconsumer.multiple;


public class Mutil_Producer implements Runnable {
	private KaoyaResourceByLock r;

	Mutil_Producer(KaoyaResourceByLock r) {
		this.r = r;
	}

	public void run() {
		while (true) {
			r.product("北京烤鸭");
		}
	}
}
