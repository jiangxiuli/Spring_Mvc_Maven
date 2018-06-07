package com.zhht.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zhht.project.junit.TestJunit;

@Service
public class AService implements AS {

    @Autowired
    TestJunit testJunit;

    @Override
    @Cacheable(value = "redisOperationsservice", key = "#password")
    public String nihao(String name, String password) throws Exception {
        if (name != null && password != null && name.equals("jxl") && password.equals("123456")) {
            return "login success";
        }
        System.out.println("login failure");
        testJunit.nihao();
        return "login failure";
    }

}
