from typing import Optional

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def sumNumbers(self, root: Optional['TreeNode']) -> int:
        
        def dfs(node, current_sum):
            # Base case for null nodes
            if not node:
                return 0
            
            # Shift the previous digits left and add the current node's value
            current_sum = current_sum * 10 + node.val
            
            # If it's a leaf node, return the completed number for this path
            if not node.left and not node.right:
                return current_sum
            
            # Continue the search down both branches and add their totals together
            return dfs(node.left, current_sum) + dfs(node.right, current_sum)
        
        # Start the DFS from the root with an initial sum of 0
        return dfs(root, 0)