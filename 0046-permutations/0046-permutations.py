from typing import List

class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        res = []
        
        def backtrack(index: int):
            # Base Case: all positions are fixed
            if index == len(nums):
                # Append a copy of the current state of nums
                res.append(nums[:])
                return
            
            # Try placing every available number in the current 'index' position
            for i in range(index, len(nums)):
                # 1. Place the number (Swap)
                nums[index], nums[i] = nums[i], nums[index]
                
                # 2. Recurse to fill the next positions
                backtrack(index + 1)
                
                # 3. Backtrack (Undo the swap) to restore original state
                nums[index], nums[i] = nums[i], nums[index]
                
        # Start backtracking from the 0th index
        backtrack(0)
        return res