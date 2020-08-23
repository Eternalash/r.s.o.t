package com.nodeReverse;

import lombok.Getter;
import lombok.Setter;

/**
 * Author Bryan.C <br>
 * Date 2018/6/25 17:39
 */
@Setter
@Getter
public class Node {
  private Node next;
  private String data;

  static Node reverse(Node head){
    while (head!=null && head.next!=null){
      Node newNode=reverse(head.next);
      head.next.next=head;
      head.next=null;
      return newNode;
    }
    return head;
  }

}
