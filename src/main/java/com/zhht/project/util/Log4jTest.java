package com.zhht.project.util;

import org.apache.log4j.Logger;

public class Log4jTest{

	private static Logger logger = Logger.getLogger(Log4jTest.class);

	public void testLog4j() {
		logger.debug("this is debug message");
		logger.info("this is info message");
		logger.error("this is error message");
	}
}
