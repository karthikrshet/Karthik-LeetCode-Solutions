import java.util.HashMap;
import java.util.Map;

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
    private Map<Integer, Integer> inorderIndexMap;
    private int postorderIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postorderIndex = postorder.length - 1;
        inorderIndexMap = new HashMap<>();
        
        // Populate the map for quick root index lookups
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        
        return buildTreeHelper(postorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] postorder, int inorderLeft, int inorderRight) {
        // If there are no elements to construct the subtree, return null
        if (inorderLeft > inorderRight) {
            return null;
        }

        // Get the current root value from postorder traversal and move backwards
        int rootVal = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootVal);

        // Find the index of this root in the inorder array
        int mid = inorderIndexMap.get(rootVal);

        // Recursively build the right subtree first, then the left subtree
        root.right = buildTreeHelper(postorder, mid + 1, inorderRight);
        root.left = buildTreeHelper(postorder, inorderLeft, mid - 1);

        return root;
    }
}