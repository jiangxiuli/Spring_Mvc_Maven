package com.zhht.project.test;

public class MySingletonEHan {
	public static String a = "haha";
	private static MySingletonEHan instance = new MySingletonEHan();

	private MySingletonEHan() {
	}

	public static MySingletonEHan getInstance() {
		return instance;
	}

}
