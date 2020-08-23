package com.hashmap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author Bryan.C <br>
 * Date 2018/7/9 20:17
 */
public class HashMapBootstrap {
  public static void main(String[] args){
    HashMap hashMap=new HashMap(1);
    hashMap.put(34L,"d");
    hashMap.put(60L,"a");
    hashMap.put(76L,"b");
    hashMap.put(81L,"c");
    hashMap.put(32L,"e");
    hashMap.put(35L,"f");
    hashMap.put(36L,"g");
    hashMap.put(37L,"h");
    hashMap.put(38L,"i");

    ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap(1);
    concurrentHashMap.put(34L,"d");
    concurrentHashMap.put(60L,"a");
    concurrentHashMap.put(76L,"b");
    concurrentHashMap.put(81L,"c");
    concurrentHashMap.put(32L,"e");
    concurrentHashMap.put(35L,"f");
    concurrentHashMap.put(36L,"g");
    concurrentHashMap.put(37L,"h");
    concurrentHashMap.put(38L,"i");
  }

}
