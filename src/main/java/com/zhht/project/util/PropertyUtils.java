package com.zhht.project.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

	private static Properties prop = null;

	public static String getValue(String name) throws IOException {
		init();
		return prop.getProperty(name);
	}

	public static void init() throws IOException {
		if (prop == null) {
			prop = new Properties();
			InputStream in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("err-code.properties");
			prop.load(in);
		}
	}
}