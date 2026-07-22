from typing import List

class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        current_sum = max_sum = nums[0]
        
        for num in nums[1:]:
            # Either extend the previous subarray or start a new one from the current element
            current_sum = max(num, current_sum + num)
            # Update the global maximum sum found so far
            max_sum = max(max_sum, current_sum)
            
        return max_sum