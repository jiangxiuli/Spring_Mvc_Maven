package com.zhht.project.design.observer;

interface Oberserverable {

    void addOberserver(Observer observer);

    void removeOberserver(Observer observer);

    void notifyObserver();
}
