class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        
        while (curr != null) {
            if (curr.left != null) {
                // Find the rightmost node of the left subtree
                TreeNode predecessor = curr.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                
                // Rewire: connect the predecessor's right to curr's right subtree
                predecessor.right = curr.right;
                
                // Move the left subtree to the right
                curr.right = curr.left;
                curr.left = null;
            }
            // Move to the next node on the right
            curr = curr.right;
        }
    }
}