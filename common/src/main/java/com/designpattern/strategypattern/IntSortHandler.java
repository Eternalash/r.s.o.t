package com.designpattern.strategypattern;

/**
 * Author Bryan.C <br>
 * Date 2018/5/2
 */
public class IntSortHandler implements SortHandler<int[]> {
    private int[] array;

    @Override
    public void setArray(int[] array) {
        this.array = array;
    }

    @Override
    public int getLength() {
        return array == null ? 0 : array.length;
    }

    @Override
    public boolean needSwap(int index) {
        return array != null && (array[index] > array[index + 1]);
    }

    @Override
    public void swap(int index) {
        int temp = array[index];
        array[index] = array[index + 1];
        array[index + 1] = temp;
    }
}
