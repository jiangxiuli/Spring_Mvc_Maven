package com.zhht.project.thread;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = { "com.zhht.project.thread" })
@ImportResource(value = { "classpath:spring-mybatis.xml","classpath:spring-mvc.xml" })
@EnableScheduling
public class MultiThreadConfig {
}