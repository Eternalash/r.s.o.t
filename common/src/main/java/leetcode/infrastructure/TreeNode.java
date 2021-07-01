package leetcode.infrastructure;

/**
 * @author: bryan.c
 * @date: 2021/2/14 下午1:35
 * @package: leecode.infrastructure
 */
public class TreeNode {
    public int val;
    public TreeNode left,right;

    public TreeNode(int val){
        this.val=val;
    }

    void traverse(TreeNode node){
        if(node==null) return;
        System.out.println("前序"+node.val);
        traverse(node.left);
//        System.out.println("中序"+node.val);
        traverse(node.right);
//        System.out.println("后序"+node.val);
    }

    public static void main(String[] args){
        TreeNode node=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        TreeNode node7=new TreeNode(7);

        node.left=node2;
        node.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;

        node.traverse(node);
    }
}
