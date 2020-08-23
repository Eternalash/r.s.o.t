package com.designpattern.bridge;

public class BridgeMain {
    public static void main(String[] args) {
        Implementor imp = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(imp);
        abs.request();
    }
}
