package com.zhht.project.http;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.JSONUtils;


public class JsonUtil {

	public static Object fromJson(String json, Class<?> clazz) {
		return fromJson(json, clazz, null);
	}
	
	public static Object fromJson(String json,Class<?> clazz,Map<String,Class<?>> map){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateFormater()); 
		generate(jsonConfig);
		String[] dateFormats = new String[] {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd","HH:mm:ss"};  
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpherEx(dateFormats,(Date) null)); 
		return JSONObject.toBean(JSONObject.fromObject(json,jsonConfig),clazz,map);
	}
	
	public static String toJson(Object obj){
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateFormater()); 
		generate(jsonConfig);
		return JSONObject.fromObject(obj,jsonConfig).toString();
	}
	
	public static Object[] fromArray(String json, Class clazz){
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateFormater()); 
		generate(jsonConfig);
		return (Object[])JSONArray.toArray(JSONArray.fromObject(json,jsonConfig), clazz);
	}

	/*
	 * 对象的属性为Integer类型属性时，如果属性值为null，则json化的字符串属性值为null
	 * */
	private static void generate(JsonConfig jsonConfig){
		DefaultValueProcessor defaultValueProcessor=new DefaultValueProcessor() {
			@Override
			public Object getDefaultValue(Class arg0) {
				return null;
			}
		};
		jsonConfig.registerDefaultValueProcessor(String.class, defaultValueProcessor);
		jsonConfig.registerDefaultValueProcessor(Integer.class, defaultValueProcessor);
		jsonConfig.registerDefaultValueProcessor(Double.class, defaultValueProcessor);
		jsonConfig.registerDefaultValueProcessor(Float.class, defaultValueProcessor);
		jsonConfig.registerDefaultValueProcessor(List.class, defaultValueProcessor);
		jsonConfig.registerDefaultValueProcessor(Date.class, defaultValueProcessor);
	}
	
}
