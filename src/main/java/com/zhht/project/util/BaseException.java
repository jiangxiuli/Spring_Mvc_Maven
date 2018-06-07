package com.zhht.project.util;

import java.io.IOException;

public abstract class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String systemCode;

	private String serviceCode;

	private String errorType;

	private String errorCode;

	private String message;

	public BaseException() {
	}
	
	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		try {
			return systemCode + "_" + serviceCode + "_" + errorType + "_"
					+ errorCode + " : " + PropertyUtils.getValue(errorCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorCode;
	}

	public BaseException(String systemCode, String serviceCode,
			String errorType, String errorCode) {
		super();
		this.systemCode = systemCode;
		this.serviceCode = serviceCode;
		this.errorType = errorType;
		this.errorCode = errorCode;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
