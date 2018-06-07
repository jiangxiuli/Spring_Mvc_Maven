package com.zhht.project.design.observer;

import java.util.ArrayList;
import java.util.List;

public class WechatServer implements Oberserverable {

    private String message;
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObserver();
    }

    public WechatServer() {
        super();
        observerlist = new ArrayList<Observer>();
    }

    private List<Observer> observerlist;

    @Override
    public void addOberserver(Observer observer) {
        observerlist.add(observer);
    }

    @Override
    public void removeOberserver(Observer observer) {
        if (!observerlist.isEmpty()) {
            observerlist.remove(observer);
        }        
    }

    @Override
    public void notifyObserver() {
        System.out.println("开始通知消息" + message);
        for (Observer observer : observerlist) {
            observer.update(message);
        }

    }

}
