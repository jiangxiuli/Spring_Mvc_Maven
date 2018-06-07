package com.zhht.project.test;

import java.util.concurrent.Callable;

import com.zhht.project.bean.AppCar;

public class TestCallable implements Callable<AppCar>{

	private AppCar appCar = new AppCar();
	@Override
	public AppCar call() throws Exception {		
		appCar.setPlateNumber("jiangeeeee");
		appCar.setCarId("jxl");
		return appCar;
	}

}
