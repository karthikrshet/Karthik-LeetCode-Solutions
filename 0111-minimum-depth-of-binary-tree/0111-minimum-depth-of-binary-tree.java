/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // If left child is null, recurse down the right subtree
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        
        // If right child is null, recurse down the left subtree
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        
        // If both children exist, take the minimum of both paths
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}