package com.zhht.project.proxy2;

/**
 * 委托类(包含业务逻辑)
 * 
 * @author Administrator
 * 
 */
public class CountImpl implements Count {

    @Override
    public void queryCount() {
        System.out.println("查看账户...");

    }

    @Override
    public void updateCount() {
        System.out.println("修改账户...");

    }

}