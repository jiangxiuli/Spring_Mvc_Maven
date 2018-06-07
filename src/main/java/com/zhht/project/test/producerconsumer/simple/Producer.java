package com.zhht.project.test.producerconsumer.simple;

public class Producer implements Runnable{

	private KaoYaResource r;
	public Producer(KaoYaResource r) {
		super();
		this.r = r;
	}
	@Override
	public void run() {
		while (true){
			try {
				r.product("北京烤鸭");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
