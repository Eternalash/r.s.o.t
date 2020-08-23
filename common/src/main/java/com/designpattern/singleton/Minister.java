package com.designpattern.singleton;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
public class Minister {
    public static void main(String[] args) {
        Emperor emperor = Emperor.getSINGLETON();
        Emperor.say();

        for (int i = 0; i < 10; i++) {
            LimitEmperor limitEmperor = LimitEmperor.getSINGLETON();
            LimitEmperor.say();
        }
    }
}
