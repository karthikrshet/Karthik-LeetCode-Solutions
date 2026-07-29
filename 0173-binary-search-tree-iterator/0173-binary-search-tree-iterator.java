import java.util.Stack;

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
class BSTIterator {
    
    // Stack to keep track of the path to the current node
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        // Initialize the stack with the leftmost path from the root
        pushAllLeft(root);
    }
    
    public int next() {
        // The top of the stack is always the next smallest element
        TreeNode node = stack.pop();
        
        // If the node has a right child, process its left branch
        if (node.right != null) {
            pushAllLeft(node.right);
        }
        
        return node.val;
    }
    
    public boolean hasNext() {
        // If the stack is not empty, there are more elements to iterate over
        return !stack.isEmpty();
    }
    
    // Helper method to push all left children of a given node onto the stack
    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */