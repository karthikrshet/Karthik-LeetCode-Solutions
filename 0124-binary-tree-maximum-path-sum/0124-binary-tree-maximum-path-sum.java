class Solution {
    private int maxPathSum;
    
    public int maxPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        calculateMaxGain(root);
        return maxPathSum;
    }
    
    private int calculateMaxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        // Compute maximum gain from left and right subtrees (ignore negative paths)
        int leftGain = Math.max(0, calculateMaxGain(node.left));
        int rightGain = Math.max(0, calculateMaxGain(node.right));
        
        // Price/sum of path passing through the current node as the peak
        int currentPathSum = node.val + leftGain + rightGain;
        
        // Update the global maximum path sum found so far
        maxPathSum = Math.max(maxPathSum, currentPathSum);
        
        // Return the maximum sum extending outward to the parent node (only one branch can be chosen)
        return node.val + Math.max(leftGain, rightGain);
    }
}