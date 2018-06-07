package com.zhht.project.design.factory;

public abstract class SimpleFactory {
    public abstract Operation getOperation();
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new MultiplyFactory();
        Operation operation = simpleFactory.getOperation();
        operation.setA(2);
        operation.setB(8);
        
        System.out.println(operation.operation_a_b());
    }
}
