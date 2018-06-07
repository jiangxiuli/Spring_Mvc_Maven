package com.zhht.project.design.strategy;

public class Strategy {
    private Operation operation;
        
    Strategy(String str) {
        switch (str) {
        case "+":
            this.operation = new AddOperation();
            break;
        case "*":
            this.operation = new MutiplyOperation();
            break;
        default:
            break;
        }
    };
    
    public int getOperationResult (){
        return operation.operation_a_b();
    }
    
    public static void main(String[] args) {
        Strategy strategy = new Strategy("*");
        strategy.getOperation().setA(4);
        strategy.getOperation().setB(5);
        System.out.println(strategy.getOperationResult());
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

}
