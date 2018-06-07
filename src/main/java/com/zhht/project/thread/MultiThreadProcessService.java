package com.zhht.project.thread;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MultiThreadProcessService {

    public static final Logger logger = Logger.getLogger(MultiThreadProcessService.class);
    
    /**
     * 默认处理流程耗时1000ms
     */
    public void processSomething() {
        System.out.println("MultiThreadProcessService-processSomething" + Thread1.currentThread() + "......start");
        try {
            Thread1.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MultiThreadProcessService-processSomething" + Thread1.currentThread() + "......end");
    }
}