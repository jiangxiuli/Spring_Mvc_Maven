package com.zhht.project.design.factory;

public abstract class Operation {
    protected int a;
    protected int b;
    
    public abstract int operation_a_b ();

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Operation() {
        super();
    }
}
