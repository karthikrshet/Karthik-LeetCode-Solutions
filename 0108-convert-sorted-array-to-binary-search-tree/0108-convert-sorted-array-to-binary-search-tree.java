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
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }
    
    private TreeNode buildTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        
        // Find the middle element to ensure height balance
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        
        // Recursively build left and right subtrees
        node.left = buildTree(nums, left, mid - 1);
        node.right = buildTree(nums, mid + 1, right);
        
        return node;
    }
}