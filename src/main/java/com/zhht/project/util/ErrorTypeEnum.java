package com.zhht.project.util;

public enum ErrorTypeEnum {
	SYSTEM("01"),
	APPLICATION("02"),
	SERVICE("03"),
	DEPNED("04"),
	WEB("05");
	
	private String code;

	public String getCode() {
		return code;
	}

	private ErrorTypeEnum(String code) {
		this.code = code;
	}
	
	public ErrorTypeEnum getEnumTest(int code){
		ErrorTypeEnum[] enumTests = ErrorTypeEnum.values();
		for (ErrorTypeEnum enumTest : enumTests) {
			if (enumTest.getCode().equals(code)) {
				return enumTest;
			}
		}
		
		return null;
	}
}
