package com.designpattern.singleton;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
public class Emperor {
    /**饿汉模式*/
    private static final Emperor EMPEROR = new Emperor();

    private Emperor() {
    }

    public static Emperor getSINGLETON() {
        return EMPEROR;
    }

    public static void say(){
        System.out.println("king of the world!");
    }
}
