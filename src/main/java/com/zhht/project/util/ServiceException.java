package com.zhht.project.util;

public class ServiceException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ServiceException(String systemCode, String serviceCode,
			String errorCode) {

		super(systemCode, serviceCode, ErrorTypeEnum.SERVICE.getCode(),
				errorCode);
	}
}
