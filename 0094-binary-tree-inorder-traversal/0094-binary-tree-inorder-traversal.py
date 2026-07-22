from typing import List, Optional

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def inorderTraversal(self, root: Optional['TreeNode']) -> List[int]:
        res = []
        stack = []
        curr = root
        
        # Continue if there are nodes to process (curr) or nodes waiting in the stack
        while curr or stack:
            # 1. Reach the leftmost node of the current Node
            while curr:
                stack.append(curr)
                curr = curr.left
                
            # 2. Curr must be None at this point, so we pop from the stack
            curr = stack.pop()
            
            # 3. Process the node
            res.append(curr.val)
            
            # 4. Visit the right subtree
            curr = curr.right
            
        return res