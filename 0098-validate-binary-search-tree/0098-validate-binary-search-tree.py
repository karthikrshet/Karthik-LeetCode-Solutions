from typing import Optional
import math

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def isValidBST(self, root: Optional['TreeNode']) -> bool:
        
        def validate(node, low=-math.inf, high=math.inf):
            # Empty nodes are valid
            if not node:
                return True
            
            # The current node's value must be strictly inside the boundaries
            if node.val <= low or node.val >= high:
                return False
            
            # Left subtree must be strictly less than node.val
            # Right subtree must be strictly greater than node.val
            return (validate(node.left, low, node.val) and 
                    validate(node.right, node.val, high))
        
        return validate(root)