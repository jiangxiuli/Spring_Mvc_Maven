package com.zhht.project.util;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ListenableFutureTest {
	public static void main(String[] args) throws InterruptedException {
		ListeningExecutorService pool = MoreExecutors
				.listeningDecorator(Executors.newFixedThreadPool(10));

		final ListenableFuture<String> future = pool
				.submit(new Callable<String>() {
					@Override
					public String call() throws Exception {
						Thread.sleep(1000 * 2);
						return "Task done !";
					}
				});
		Thread.sleep(5 * 1000); // wait for task done
		pool.shutdown();
	}
}
