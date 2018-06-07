package com.zhht.project.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zhht.project.junit.BaseJunit;
import com.zhht.project.redis.RedisCacheService;
import com.zhht.test.bean.Account;
import com.zhht.test.dao.AccountMapper;

@Service
public class TestDAO extends BaseJunit {
    @Autowired
    AccountMapper accountDao;

    @Autowired
    RedisCacheService redisCacheService;

    @Test
    public void test() {
        Account account = accountDao.selectByPrimaryKey(6);
        System.out.println(account.toString());
        // account.setId(6);
        // account.setName("jxlzjj");
        // account.setBalance(1999l);
        // accountDao.insert(account);

        Object object = redisCacheService.get("methodName");
        if (object != null) {
            System.out.println(object.toString());
        }

    }
}
