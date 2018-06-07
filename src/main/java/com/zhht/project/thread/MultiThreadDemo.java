package com.zhht.project.thread;
/**
 * 多线程并发处理demo
 * @author daniel.zhao
 *
 */
public class MultiThreadDemo implements Runnable {

    private MultiThreadProcessService multiThreadProcessService;
    
    public MultiThreadDemo() {
    }
    
    public MultiThreadDemo(MultiThreadProcessService multiThreadProcessService) {
        this.multiThreadProcessService = multiThreadProcessService;
    }
    
    @Override
    public void run() {
        multiThreadProcessService.processSomething();
    }

}