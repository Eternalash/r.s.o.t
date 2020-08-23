package com.designpattern.command;

/**
 * Author Bryan.C <br>
 * Date 2018/5/9
 */
public class MyCommand extends Command {

    @Override
    public void execute() {
        super.receiverA.action();
        super.receiverB.action();
    }
}
