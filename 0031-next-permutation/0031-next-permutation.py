from typing import List

class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        n = len(nums)
        i = n - 2
        
        # 1. Find the first decreasing element from the right
        while i >= 0 and nums[i] >= nums[i + 1]:
            i -= 1
            
        # If we found a valid index 'i', find the element to swap it with
        if i >= 0:
            j = n - 1
            # 2. Find the first element from the right that is greater than nums[i]
            while nums[j] <= nums[i]:
                j -= 1
            # 3. Swap them
            nums[i], nums[j] = nums[j], nums[i]
            
        # 4. Reverse the suffix (from i+1 to the end of the array)
        left, right = i + 1, n - 1
        while left < right:
            nums[left], nums[right] = nums[right], nums[left]
            left += 1
            right -= 1