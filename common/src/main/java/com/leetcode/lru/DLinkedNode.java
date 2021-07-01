package com.leetcode.lru;

/**
 * @Author:bryan.c
 * @Date:2021/3/8
 */
public class DLinkedNode {
    public DLinkedNode prev;
    public DLinkedNode next;
    public int key;
    public int value;

    public DLinkedNode() {
    }

    public DLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
