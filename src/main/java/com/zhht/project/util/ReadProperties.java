package com.zhht.project.util;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 读取properties文件
 * 
 * @author WangShengguang
 * @version v1.0
 *
 */
public class ReadProperties {

	private static Logger log = LoggerFactory.getLogger(ReadProperties.class);
	
	/**
	 * 加载properties文件
	 * 
	 * @param propertiesFile	文件名称
	 * @return
	 */
	public static Properties loadProperties(String fileName) {
		Properties properties = null;
		try {
			properties = PropertiesLoaderUtils.loadAllProperties(fileName);
		} catch (Exception e) {
			log.error("File <"+fileName+"> load failed.",e);
		}
		return properties;
	}
	
}
