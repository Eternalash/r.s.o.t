package leetcode.infrastructure;

/**
 * @Author:bryan.c
 * @Date:2020/11/19
 */
public class ListNode extends Object {
    public int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }


    public String string() {
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
