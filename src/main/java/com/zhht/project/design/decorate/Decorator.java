package com.zhht.project.design.decorate;

public abstract class Decorator implements Component {

    private Component component;

    public Component getConcreteComponent() {
        return component;
    }

    public void setConcreteComponent(Component concreteComponent) {
        this.component = concreteComponent;
    }

    @Override
    public void operation() {
        if (component != null) {
            component.operation();
        }
    }

    public static void main(String[] args) {
        Component component = new ConcreteComponent();

        ConcreteDecoratorA concreteDecoratorA = new ConcreteDecoratorA();
        ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB();

        concreteDecoratorA.setConcreteComponent(component);
        concreteDecoratorB.setConcreteComponent(concreteDecoratorA);
        concreteDecoratorB.operation();
    }
}
