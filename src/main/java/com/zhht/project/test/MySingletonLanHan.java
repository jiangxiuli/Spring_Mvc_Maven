package com.zhht.project.test;

public class MySingletonLanHan {
	private static MySingletonLanHan instance = null;

	private MySingletonLanHan() {
	}

	public static MySingletonLanHan getInstance() {
		if (instance == null) {// 懒汉式
			instance = new MySingletonLanHan();
		}
		return instance;
	}
}
