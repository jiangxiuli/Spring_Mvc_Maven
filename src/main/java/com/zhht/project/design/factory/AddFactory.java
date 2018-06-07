package com.zhht.project.design.factory;

public class AddFactory extends SimpleFactory{

    @Override
    public Operation getOperation() {
        return new AddOperation();
    }

}
