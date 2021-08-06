package leetcode;

import leetcode.infrastructure.ListNode;

/**
 * Author:bryan.c
 * Date:2021/8/6
 */
public class LeetCode92 {
    private final static LeetCode92 INSTANCE=new LeetCode92();

    ListNode successor = null; // 后驱节点

    public static void main(String... args){
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        head.next.next.next.next.next=new ListNode(6);
        System.out.println(INSTANCE.reverse(head).string());

        head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        head.next.next.next.next.next=new ListNode(6);
        System.out.println(INSTANCE.reverseN(head,4).string());


        head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        head.next.next.next.next.next=new ListNode(6);
//        System.out.println(INSTANCE.reverseBetween(head,2,5).string());

    }


    /**
     *重排前n个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode reverseN(ListNode head,int n){
        if(n==1) {
            // 记录第 n + 1 个节点
            successor=head.next;
            return head;
        }
        ListNode last=reverseN(head.next,n-1);
        head.next.next=head;
        head.next=successor;
        return last;
    }

    /**
     * 重排1<=left->right<=ListNode.length
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head,int left,int right){
        if(left==1){
            return reverseN(head,right);
        }
        head.next=reverseBetween(head.next,left-1,right-1);
        return head;
    }

    /**
     * 全重排
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head){
        if(head.next==null) return head;
        ListNode last=reverse(head.next);
        head.next.next=head;
        head.next=null;//此处null可看为后驱节点
        return last;
    }
}
