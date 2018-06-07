package com.zhht.project.util;

import com.zhht.project.controller.AController;

public class HeapAndStackTest{
	public void AValueToBValue(int a){
		int b = 10;
		a = b;
	}
	
	public void AClassToBClass(AController a){
		a.setStr("a str change");
	}
	
//	@Test
	public void testChange(){
		int a = 5;
		AValueToBValue(a);
		System.out.println(a);
		
		AController aClass = new AController();
		aClass.setStr("a original");
		AClassToBClass(aClass);
		System.out.println(aClass.getStr());
	}

}
