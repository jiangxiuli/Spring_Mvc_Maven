package com.zhht.project.util;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

public class SysConfig {

	private static Properties properties;
	
	private static void init(){
		if(properties == null)
			properties = ReadProperties.loadProperties("redis-config.properties");
	}
	
	public static String getString(String key){
		init();
		String val = properties.getProperty(key);
		if(!StringUtils.isStrTrimNull(val)){
			val = val.trim();
		}
		return val;
	}
	
	/**
	 * 修改/添加新属性
	 * 如果是新增返回null
	 * 如果是修改返回原属性值
	 * 
	 * @param key
	 * @param value
	 */
	public static Object setPropertis(String key, String value)
	{
		init();
		return properties.setProperty(key, value);
	}
	
	/**
	 * 更新属性值，在原有值的后面添加
	 * 
	 * @param key
	 * @param separator	分隔符
	 * @param value
	 * @return
	 */
	public static Object updateProperties(String key, String separator, String value)
	{
		init();
		return properties.setProperty(key, getString(key) + separator + value);
	}
	
	public static HashMap<Object,Object> readProperties(){
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		init();
		for(Entry<Object, Object> a : properties.entrySet()){
			map.put(a.getKey(), a.getValue());
		}
		return map;
	}
	
	/**
	 * 返回int类型的值
	 * @param key
	 * @return
	 */
	public static int getInt(String key){
		String val  = getString(key);
		if(StringUtils.isStrTrimNull(val)){
			throw new RuntimeException(key+" not defined"); 
		}
		boolean f = StringUtils.isInteger(val);
		if(f){
			return Integer.valueOf(val);
		}
		throw new NumberFormatException(val+ "not a Integer value");
	}
	/**
	 * 返回long类型的值
	 * @param key
	 * @return
	 */
	public static long getLong(String key) {
		String val = getString(key);
		if(StringUtils.isStrTrimNull(val)) {
			throw new RuntimeException(key+" not defined"); 
		}
		boolean f = StringUtils.isInteger(val);
		if(f){
			return Long.valueOf(val);
		}
		throw new NumberFormatException(val+ "not a Long value.");
	}
	
	public static boolean getBoolean(String key) {
		String val = getString(key);
		return Boolean.parseBoolean(val);
	}
	
	public static void main(String[] args) {
//		System.out.println(SysConfig.getString("BFB_SP_KEY"));
		SysConfig.readProperties();
		System.out.println();
	}
}
