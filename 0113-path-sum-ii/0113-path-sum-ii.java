import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), result);
        return result;
    }
    
    private void dfs(TreeNode node, int targetSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        
        currentPath.add(node.val);
        
        // Check if it's a leaf node and matches the target sum
        if (node.left == null && node.right == null && node.val == targetSum) {
            result.add(new ArrayList<>(currentPath));
        } else {
            dfs(node.left, targetSum - node.val, currentPath, result);
            dfs(node.right, targetSum - node.val, currentPath, result);
        }
        
        // Backtrack
        currentPath.remove(currentPath.size() - 1);
    }
}