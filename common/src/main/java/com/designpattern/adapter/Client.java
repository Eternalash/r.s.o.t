package com.designpattern.adapter;

/**
 * Author Bryan.C <br>
 * Date 2018/5/13
 */
public class Client {
    public static void main(String[] args) {
        /*
          原有的业务逻辑
         */
        Target target = new ConcreteTarget();
        target.request();
        /*
          增加了适配器角色后的业务逻辑
         */
        Target target1 = new ClassAdapter();
        target1.request();
    }
}
