package com.zhht.project.design.template;

public abstract class AbstractClass {
    protected abstract void doSomething();
    protected abstract void doAnything();
    
    protected final void templateMethod(){
        doSomething();
        doAnything();
    }
}
