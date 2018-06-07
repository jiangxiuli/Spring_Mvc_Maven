package com.zhht.project.test;

public class Outer {
	public void testOut(){
		System.out.println("this is out");
	}
	public class Inner {
		public void testIn(){
			System.out.println("this is in");
		}
	}
	
	public static void main(String[] args) {
		Outer outer = new Outer();
		Inner inner = outer.new Inner();
		inner.testIn();
	}
}
