package leetcode.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:bryan.c
 * @Date:2021/3/8
 */
public class LRUCache {
    private int capacity;
    private int size;
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private DLinkedNode head = new DLinkedNode();
    private DLinkedNode tail = new DLinkedNode();

    class DLinkedNode {
        DLinkedNode prev;
        DLinkedNode next;
        int key;
        int value;

        public DLinkedNode() {
        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public void put(int key,int value){
        DLinkedNode node=cache.get(key);
        if(node==null){
            node=new DLinkedNode(key,value);
            cache.put(key,node);
            addToHead(node);
            size++;
            if(size>capacity){
                DLinkedNode tail=removeTail();
                cache.remove(tail.key);
                --size;
            }
        }else{
            node.value=value;
            moveToHead(node);
        }
    }

    public int get(int key) throws Exception {
        DLinkedNode node=cache.get(key);
        if(node==null)
            throw new Exception("not exist");
        moveToHead(node);
        return node.value;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    private void addToHead(DLinkedNode node) {
        node.prev=head;
        node.next=head.next;
        head.next.prev=node;
        head.next=node;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

}
