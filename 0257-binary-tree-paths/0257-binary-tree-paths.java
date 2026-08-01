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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root != null) {
            dfs(root, "", result);
        }
        return result;
    }
    
    private void dfs(TreeNode node, String path, List<String> result) {
        path += node.val;
        
        // If it's a leaf node, add the path to the result
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }
        
        // Otherwise, continue DFS down non-null children with arrow separator
        if (node.left != null) {
            dfs(node.left, path + "->", result);
        }
        if (node.right != null) {
            dfs(node.right, path + "->", result);
        }
    }
}