package com.designpattern.templatemethod;

import java.util.Arrays;
import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/5/2
 */
public class Sort {
    public static void main(String[] args){
        //对整型数组排序
        int[] intArray = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int operations = new IntBubbleSorter().sort(intArray);
        System.out.println("[Template Method] operations:" + operations + ", array:" + Arrays.toString(intArray));


        //对List集合排序
        List<Integer> list = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        operations = new IntegerListBubbleSorter().sort(list);
        System.out.println("[Template Method] operations:" + operations + ", list:" + list.toString());
    }
}
