# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        # If both nodes are None, they are identical up to this point
        if not p and not q:
            return True
        
        # If one is None and the other isn't, or if their values differ, they are not the same
        if not p or not q or p.val != q.val:
            return False
        
        # Recursively check the left children AND the right children
        return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)