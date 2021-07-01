package com;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Bryan.C <br>
 * Date:2020/3/6
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(Lists.newArrayList("a","b","c").containsAll(Lists.newArrayList("a","b")));
        System.out.println(Lists.newArrayList("a","b").containsAll(Lists.newArrayList("a","b","c")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        System.out.println(String.format("{}-{}","a","b"));


        Map<String, List<String>> map=new HashMap<>();
        map.put("a", Lists.newArrayList("1","2","3"));
        map.put("b", Lists.newArrayList("4","5","6"));

        System.out.println(new Gson().toJson(map));
    }
}
