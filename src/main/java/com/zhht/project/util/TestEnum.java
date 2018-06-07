package com.zhht.project.util;

public enum TestEnum {
	NI(1),
	WO(2),
	TA(3);
	
	private int code;

	public int getCode() {
		return code;
	}

	private TestEnum(int code) {
		this.code = code;
	}
	
	public TestEnum getEnumTest(int code){
		TestEnum[] enumTests = TestEnum.values();
		for (TestEnum enumTest : enumTests) {
			if (enumTest.getCode() == code) {
				return enumTest;
			}
		}
		
		return null;
	}
}
