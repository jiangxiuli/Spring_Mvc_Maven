package com.zhht.project.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhht.project.bean.AppCar;
import com.zhht.project.service.AS;
import com.zhht.project.util.BaseException;

@Controller
@RequestMapping("/test1")
public class AController {
    private static final Logger logger = LoggerFactory.getLogger(AController.class);

	@RequestMapping("/nihao")
	@ResponseBody
	@CacheEvict(value = "redisOperations1", key = "#name")
	public String nihao(String name, String password) {
		try {
			String nihao = aService.nihao(name, password);
		} catch (Exception e) {
			System.out.println("我是捕获到的异常");
			e.printStackTrace();
		}
		return "buhao";
	}

	private int a;

	private String str;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	@Override
	public String toString() {
		return "AController [a=" + a + ", str=" + str + "]";
	}

	public AController() {
		super();
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Resource
	AS aService;

	public AController(int a, String str) {
		super();
		this.a = a;
		this.str = str;
	}

	@RequestMapping("/selectcar")
	@ResponseBody
	public AppCar selectcar(String carId) throws IOException {
		AppCar appCar = null;

		try {
			// appCar = aService.selectcar(carId);
		} catch (BaseException e) {
			// log.error("", e);
		}
		logger.error("----------------------------------------------------");
		return appCar;
	}

	@RequestMapping("/selectcar1")
	@ResponseBody
	public AppCar selectcar1(String carId) throws IOException {
		AppCar appCar = null;

		try {
			// appCar = aService.selectcar(carId);
		} catch (BaseException e) {
			// log.error("", e);
		}
		logger.error("----------------------------------------------------");
		return appCar;
	}

	public static void main(String[] args) throws InterruptedException {
		// Object转换成String
		/*
		 * AController aController = new AController(1, "jiangxiuli"); String
		 * jsonString = JSON.toJSONString(aController);
		 * System.out.println(jsonString);
		 */

		// String转换成Object
		/*
		 * AController bController = JSON.parseObject(jsonString,
		 * AController.class); System.out.println(bController.toString());
		 */

		// Map -> JSON
		/*
		 * HashMap<String, String> map = new HashMap<String, String>();
		 * map.put("a", "zhongshenghai"); map.put("b", "10"); map.put("c", "1");
		 * String json = JSON.toJSONString(map);
		 * System.out.println(json.toString());
		 */
		// JSON -> Map
		/*
		 * Map<String,Object> map1 = (Map<String,Object>)JSON.parse(string1);
		 * for (String key : map1.keySet()) {
		 * System.out.println(key+":"+map1.get(key)); }
		 */
		System.out.println(" i am main");
	}
}
