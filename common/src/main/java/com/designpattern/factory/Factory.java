package com.designpattern.factory;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
public abstract class Factory {
    public abstract <T extends Human> T createHuman(Class<T> c);
}
