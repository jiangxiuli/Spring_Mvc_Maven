package com.zhht.project.design.template;

public class Main {
    public static void main(String[] args) {
        AbstractClass abstractClass1 = new ConcreteClass1();
        AbstractClass abstractClass2 = new ConcreteClass2();

        abstractClass1.templateMethod();
        abstractClass2.templateMethod();
    }
}
