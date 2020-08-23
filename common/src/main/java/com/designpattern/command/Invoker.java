package com.designpattern.command;

/**
 * Author Bryan.C <br>
 * Date 2018/5/9
 */
public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action() {
        command.execute();
    }
}
