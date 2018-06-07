package com.zhht.project.object;

public abstract class Animal {

    protected abstract void shout();
    
    public static void main(String[] args) {
       Animal cat = new Cat();
       Animal dog = new Dog();
       cat.shout();
       dog.shout();
    }
}
