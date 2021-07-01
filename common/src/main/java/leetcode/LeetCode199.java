package leetcode;

import leetcode.infrastructure.TreeNode;

import java.util.*;

/**
 * @author: bryan.c
 * @date: 2021/6/30 下午7:04
 * @package: com.leetcode
 */
public class LeetCode199 {
    public static void main(String[] args){
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.right=new TreeNode(5);
        root.right.right=new TreeNode(4);
        System.out.println(rightSideView(root));
    }


    public static List<Integer> rightSideView(TreeNode root) {
//        return dfs(root);
        return bfs(root);
    }

    /**
     * 广度优先
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
                if(null!=node.left){
                    queue.offer(node.left);
                }
                if(null!=node.right){
                    queue.offer(node.right);
                }
                if(i==size-1){
                    res.add(node.val);
                }
            }
        }
        return res;
    }


    /**
     * 深度优先
     * @param root
     * @return
     */
    public static List<Integer> dfs(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;

        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        Stack<Integer> depthStack = new Stack<Integer>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if (node != null) {
                // 维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);

                // 如果不存在对应深度的节点我们才插入
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }

                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }

        List<Integer> rightView = new ArrayList<Integer>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }
}
