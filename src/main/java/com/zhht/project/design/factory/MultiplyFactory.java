package com.zhht.project.design.factory;

public class MultiplyFactory extends SimpleFactory{

    @Override
    public Operation getOperation() {
        return new MutiplyOperation();
    }

}
