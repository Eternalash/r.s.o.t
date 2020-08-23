package com.designpattern.command;

/**
 * Author Bryan.C <br>
 * Date 2018/5/9
 */
public class Receiver  {
    private String name;
    public Receiver(String name){
        this.name=name;
    }
    public void action() {
        System.out.println(name+ "\tcommand received!");
    }
}
