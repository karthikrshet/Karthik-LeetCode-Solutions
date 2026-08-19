# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        # Base case
        if not root:
            return None
        
        # Swap the children
        root.left, root.right = root.right, root.left
        
        # Recurse for the subtrees
        self.invertTree(root.left)
        self.invertTree(root.right)
        
        return root