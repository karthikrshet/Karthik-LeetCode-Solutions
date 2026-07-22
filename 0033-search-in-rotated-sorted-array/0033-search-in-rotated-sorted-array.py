from typing import List

class Solution:
    def search(self, nums: List[int], target: int) -> int:
        left, right = 0, len(nums) - 1
        
        while left <= right:
            mid = (left + right) // 2
            
            # Target found
            if nums[mid] == target:
                return mid
            
            # Check if the left half is the sorted half
            if nums[left] <= nums[mid]:
                # Check if target is strictly within the sorted left bounds
                if nums[left] <= target < nums[mid]:
                    right = mid - 1  # Target is in the left half
                else:
                    left = mid + 1   # Target is in the right half
            
            # Otherwise, the right half must be the sorted half
            else:
                # Check if target is strictly within the sorted right bounds
                if nums[mid] < target <= nums[right]:
                    left = mid + 1   # Target is in the right half
                else:
                    right = mid - 1  # Target is in the left half
                    
        return -1