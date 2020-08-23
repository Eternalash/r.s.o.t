package com.designpattern.templatemethod;

import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/5/2
 */
public class IntegerListBubbleSorter extends BubbleSorter<List<Integer>> {
    private List<Integer> list;

    @Override
    protected void setArray(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected int getLength() {
        return list == null ? 0 : list.size();
    }

    @Override
    protected boolean needSwap(int index) {
        return list != null && (list.get(index) > list.get(index + 1));
    }

    @Override
    protected void swap(int index) {
        int temp = list.get(index);
        list.set(index, list.get(index + 1));
        list.set(index + 1, temp);
    }
}
