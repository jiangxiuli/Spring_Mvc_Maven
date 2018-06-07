package com.zhht.project.test.producerconsumer.simple;

public class Consumer implements Runnable {
	private KaoYaResource r;

	public Consumer(KaoYaResource r) {
		super();
		this.r = r;
	}

	@Override
	public void run() {
		while(true){
			try {
				r.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
