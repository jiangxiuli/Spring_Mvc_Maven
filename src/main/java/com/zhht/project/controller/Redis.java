package com.zhht.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/nihao")
public class Redis {	

	private static Jedis jedis = null;

	public static void getJedis() {
		if (jedis != null) {
			jedis = new Jedis("192.168.42.137", 6379);
			jedis.auth("123456");
		}
		System.out.println(jedis.ping());
	}

	@RequestMapping("/setJedis")
	@ResponseBody
	public String setJedis() {
		getJedis();
		jedis.hset("myAppCar", "carId", "ba6ae4bd_ffe3_11e6_b663_6c92bf2bf639");
		// jedis.close();
		return "success";
	}

	public static void main(String[] args) {
		getJedis();
		jedis.hset("myAppCar", "carId", "ba6ae4bd_ffe3_11e6_b663_6c92bf2bf639");
		jedis.close();
		System.out.println("redis connection success");
	}	
}
