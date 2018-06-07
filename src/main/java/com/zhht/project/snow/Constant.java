package com.zhht.project.snow;

public class Constant {

	public static final String EMPTY_STRING="";
	public static String  OS_TYPE=System.getProperties().getProperty("os.name").toLowerCase();
	public static boolean LIUX_OS = OS_TYPE.contains("linux")?true:false;
	/*操作成功编码*/
	public static final String RESULT_SUCCESS = "0000";
	
	/*操作失败编码*/
	public static final String RESULT_ERROR = "9999";
}
