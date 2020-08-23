package com.designpattern.templatemethod;

/**
 * Author Bryan.C <br>
 * Date 2018/5/2
 */
public class IntBubbleSorter extends BubbleSorter<int[]> {
    private int[] array;

    @Override
    protected void setArray(int[] array) {
        this.array = array;
    }

    @Override
    protected int getLength() {
        return array == null ? 0 : array.length;
    }

    @Override
    protected boolean needSwap(int index) {
        return array != null && (array[index] > array[index + 1]);
    }

    @Override
    protected void swap(int index) {
        int temp = array[index];
        array[index] = array[index + 1];
        array[index + 1] = temp;
    }
}
