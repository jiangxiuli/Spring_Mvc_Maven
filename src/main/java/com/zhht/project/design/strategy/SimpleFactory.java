package com.zhht.project.design.strategy;

public class SimpleFactory {
    public Operation getOperation(String str) {
        Operation operation = null;
        switch (str) {
        case "+":
            operation = new AddOperation();
            break;
        case "*":
            operation = new MutiplyOperation();
            break;
        default:
            break;
        }
        
        return operation;
    };

    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Operation operation = simpleFactory.getOperation("");
        operation.setA(2);
        operation.setB(8);

        System.out.println(operation.operation_a_b());
    }
}
