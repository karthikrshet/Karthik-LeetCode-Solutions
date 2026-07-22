from typing import List

class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        res = []
        nums.sort()  # Sort to group duplicates together
        
        def backtrack(start: int, path: List[int]):
            # Every node in the recursion tree represents a valid subset
            res.append(list(path))
            
            for i in range(start, len(nums)):
                # Skip duplicates at the same level of recursion
                if i > start and nums[i] == nums[i - 1]:
                    continue
                    
                path.append(nums[i])
                backtrack(i + 1, path)
                path.pop()
                
        backtrack(0, [])
        return res