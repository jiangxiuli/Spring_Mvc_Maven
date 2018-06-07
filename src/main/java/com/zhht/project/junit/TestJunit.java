package com.zhht.project.junit;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.zhht.project.bean.AppCar;
import com.zhht.project.redis.RedisCacheService;
import com.zhht.project.test.TestCallable;

@Component
public class TestJunit extends BaseJunit {
    @Autowired
    RedisCacheService redisCacheService;
//    @Autowired
//    AService aService;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void getProjectName() {
        String fullProjectPath = System.getProperty("user.dir");
        File file = new File(fullProjectPath);
        String projectName = file.getName();
        file.delete();
        System.out.println(projectName);
    }

    // @Test
    // public void getServiceName(String fullServicePath) {
    // String[] names = fullServicePath.split("\\.");
    // String serviceName = names[names.length - 3];
    // System.out.println(serviceName);
    // }

    @Test
    public void test() {
        System.out.println("i am test");
        redisCacheService.set("testRedis", "iamredis2");

        Object object = redisCacheService.get("testRedis");
        System.out.println(object.toString());
    }

    public static void main(String[] args) {
        TestJunit TestJunit = new TestJunit();
        TestJunit.test();
        System.out.println(Integer.MAX_VALUE);
    }

    public void testThread() throws InterruptedException, ExecutionException {
        // 无返回的线程池
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(threadPoolTaskExecutor.getMaxPoolSize());
                System.out.println(threadPoolTaskExecutor.getKeepAliveSeconds());
                System.out.println("i am multiThread");
            }
        });

        // 有返回的线程池
        FutureTask<AppCar> futureTask = new FutureTask<AppCar>(new TestCallable());
        threadPoolTaskExecutor.submit(futureTask);
        AppCar appCar = futureTask.get();
        System.out.println(appCar.toString());
    }

    public void nihao() {
        System.out.println("i am test");
    }
}
