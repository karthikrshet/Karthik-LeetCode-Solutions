from typing import List, Optional

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def generateTrees(self, n: int) -> List[Optional['TreeNode']]:
        if n == 0:
            return []
            
        def generate(start, end):
            # Base case: empty subtree
            if start > end:
                return [None]
                
            all_trees = []
            
            # Pick each number to be the root
            for i in range(start, end + 1):
                # Generate all valid left and right subtrees
                left_trees = generate(start, i - 1)
                right_trees = generate(i + 1, end)
                
                # Connect every combination of left and right subtrees to the root 'i'
                for l in left_trees:
                    for r in right_trees:
                        current_tree = TreeNode(i)
                        current_tree.left = l
                        current_tree.right = r
                        all_trees.append(current_tree)
                        
            return all_trees
            
        return generate(1, n)