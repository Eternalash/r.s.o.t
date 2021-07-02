package leetcode.addtwonumbers;

/**
 * @Author:bryan.c
 * @Date:2020/11/19
 */
public class ListNode extends Object {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder("[");
        ListNode cur=this;
        while (cur.next!=null){
            stringBuilder.append(cur.val).append(",");
            cur=cur.next;
        }
        stringBuilder.append(cur.val).append("]");
        return stringBuilder.toString();
    }
}
