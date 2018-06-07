package com.zhht.project.util;


public class EnumTest{	
	public void testEnum(){
		TestEnum[] enumTests = TestEnum.values();
		for (TestEnum aEnum : enumTests) {
			System.out.println("aEnum.name()：" + aEnum.name());
			System.out.println("aEnum.ordinal()：" + aEnum.ordinal());
			System.out.println("aEnum.getCode()：" + aEnum.getCode());
			System.out.println("--------------------------------");
			System.out.println(System.nanoTime());	
			System.out.println(System.currentTimeMillis());	
		}
	}
}
