package com.designpattern.strategypattern;

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
        BubbleSorter<int[]> intBubbleSorter = new BubbleSorter<>(new IntSortHandler());
        int operations = intBubbleSorter.sort(intArray);
        System.out.println("[Strategy] operations:" + operations + ", array:" + Arrays.toString(intArray));

        //对List集合排序
        List<Integer> list = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        BubbleSorter<List<Integer>> integerListBubbleSorter = new BubbleSorter<>(new IntegerListSortHandler());
        operations = integerListBubbleSorter.sort(list);
        System.out.println("[Strategy] operations:" + operations + ", list:" + list);
    }
}
