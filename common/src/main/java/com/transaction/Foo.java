package com.transaction;

public class Foo<T> {
    int x=42;
    public void go(final int x){
        System.out.println(x);
    }

    private void testVarargs(T... args) {//编译出错
    }
}


