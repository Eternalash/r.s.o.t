package com.nodeReverse;

/**
 * Author Bryan.C <br>
 * Date 2018/6/25 17:53
 */
public class Boostrap {
  public static void main(String[] args) {
    Node nodeA = new Node();
    nodeA.setData("A");

    Node nodeB = new Node();
    nodeB.setData("B");

    Node nodeC = new Node();
    nodeC.setData("C");

    nodeA.setNext(nodeB);
    nodeB.setNext(nodeC);
    nodeC.setNext(null);

    Node reverseNode= Node.reverse(nodeA);
    System.out.println(reverseNode.getData());


    TreeNode treeNodeA=new TreeNode("A");
    TreeNode treeNodeB=new TreeNode("B");
    TreeNode treeNodeC=new TreeNode("C");
    TreeNode treeNodeD=new TreeNode("D");
    TreeNode treeNodeE=new TreeNode("E");
    TreeNode treeNodeF=new TreeNode("F");
    TreeNode treeNodeG=new TreeNode("G");

    treeNodeA.left=treeNodeB;
    treeNodeA.right=treeNodeD;
    treeNodeB.left=treeNodeC;
    treeNodeB.right=treeNodeG;
    treeNodeD.left=treeNodeE;
    treeNodeD.right=treeNodeF;

    TreeNode treeNode= TreeNode.invertTree(treeNodeA);

  }

}
