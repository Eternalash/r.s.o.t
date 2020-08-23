package com.designpattern.mediator;

/**
 * Author Bryan.C <br>
 * Date 2018/5/9
 */
public class Renter extends Colleague {
    public Renter(String name, Mediator mediator) {
        super(name, mediator);
    }


    @Override
    protected void sendMessage(String msg) {
        mediator.operation(this, msg);
    }

    @Override
    protected void getMessage(String msg) {
        System.out.println("求租者[" + name + "]收到中介发来的消息： " + msg);
    }
}
