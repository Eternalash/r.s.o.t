package com;


import com.google.common.collect.Lists;

/**
 * Author: Bryan.C <br>
 * Date:2020/3/6
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(Lists.newArrayList("a","b","c").containsAll(Lists.newArrayList("a","b")));
        System.out.println(Lists.newArrayList("a","b").containsAll(Lists.newArrayList("a","b","c")));
    }
}
