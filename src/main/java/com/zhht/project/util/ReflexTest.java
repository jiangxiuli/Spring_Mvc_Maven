package com.zhht.project.util;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.zhht.project.controller.AController;

public class ReflexTest implements Servlet{
	private ServletContext servletContext;

//	@Ignore
	public void testClass() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
        // 通过类来获取Class对象		
		Class clazz1 = Integer.class;
		
		// 通过对象来获取Class对象
		Integer i = 0;
		Class clazz2 = i.getClass();
		
		// 通过类的全路径名字获取Class对象
		Class clazz3 = Class.forName("com.zhht.test.controller.AController");
		System.out.println(clazz3.getName());
		System.out.println(clazz3.getSimpleName());
		
		// 通过Class的无参构造方法来实例化对象
		AController a = (AController) clazz3.newInstance();
		System.out.println(a.toString());
		
		// 通过Class获取构造方法，再通过构造方法实例化有参的对象
		Constructor constructor = clazz3.getConstructor(int.class, String.class);		
		AController b = (AController) constructor.newInstance(5,"success");		
		System.out.println(b.toString());
		
		Method[] methods = b.getClass().getDeclaredMethods();
		for (Method method: methods) {
			System.out.println(method.getName());
		}		
		
	}

	public void destroy() {
		
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	public void init(ServletConfig servletConfig) throws ServletException {
		servletContext = servletConfig.getServletContext();		
	}

	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		
	}
	
//	@Test
	public void testServlet(){
		
	}
}
