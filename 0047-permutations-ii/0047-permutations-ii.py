from typing import List

class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        # 1. Sort to keep duplicates adjacent for easy pruning
        nums.sort()
        
        res = []
        visited = [False] * len(nums)
        
        def backtrack(path: List[int]):
            # Base Case: Permutation is complete
            if len(path) == len(nums):
                res.append(path[:])
                return
                
            for i in range(len(nums)):
                # If we've already used this exact element in the current path, skip it
                if visited[i]:
                    continue
                    
                # Pruning Condition: 
                # If it's a duplicate and the previous identical element was NOT used 
                # in the current path segment, it means we already generated this branch.
                if i > 0 and nums[i] == nums[i - 1] and not visited[i - 1]:
                    continue
                    
                # 2. Place the number
                visited[i] = True
                path.append(nums[i])
                
                # 3. Recurse
                backtrack(path)
                
                # 4. Backtrack (Undo)
                path.pop()
                visited[i] = False
                
        backtrack([])
        return res