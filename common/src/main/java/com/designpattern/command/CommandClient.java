package com.designpattern.command;

/**
 * Author Bryan.C <br>
 * Date 2018/5/9
 */
public class CommandClient {
    public static void main(String[] args) {
        Command cmd = new MyCommand();
        Invoker invoker = new Invoker(cmd);
        invoker.action();
    }
}
