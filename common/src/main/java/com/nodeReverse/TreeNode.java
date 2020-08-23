package com.nodeReverse;

import lombok.Getter;
import lombok.Setter;

/**
 * Author Bryan.C <br>
 * Date 2018/6/26 12:38
 */
@Setter
@Getter
public class TreeNode {
    String val;
    TreeNode left;
    TreeNode right;

    TreeNode(String x) {
        val = x;
    }

    public static TreeNode invertTree(TreeNode root) {
        while (root != null) {
            invertTree(root.left);
            invertTree(root.right);
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            return root;
        }
        return null;
    }
}
