package com.designpattern.command;

/**
 * Author Bryan.C <br>
 * Date 2018/5/9
 */
public abstract class Command {
    protected Receiver receiverA=new Receiver("receiverA");
    protected Receiver receiverB=new Receiver("receiverB");
    public abstract void execute();
}
