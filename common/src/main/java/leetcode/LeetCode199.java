package leetcode;

import leetcode.infrastructure.TreeNode;

import java.util.*;

/**
 * @author: bryan.c
 * @date: 2021/6/30 下午7:04
 * @package: com.leetcode
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/
 */
public class LeetCode199 {
    static List<Integer> res = new ArrayList<>();
    public static void main(String[] args){
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.right=new TreeNode(5);
        root.right.right=new TreeNode(4);
        System.out.println(rightSideView(root));
    }


    public static List<Integer> rightSideView(TreeNode root) {
//        dfs(root, 0);
//        return res;
        return bfs(root);
    }

    /**
     * 广度优先 Breath First Search
     * @param root
     * @return
     */
    public static List<Integer> bfs(TreeNode root){
        List<Integer> res=new ArrayList<>();
        if (null==root)
            return res;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode node=queue.poll();
                if(null!=node.right){
                    queue.offer(node.right);
                }
                if(null!=node.left){
                    queue.offer(node.left);
                }
                if(i==0){
                    res.add(node.val);
                }
            }
        }
        return res;
    }


    /**
     * 深度优先 Deep First Search
     * @param root
     * @return
     */
    private static void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == res.size()) {   // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }
}
