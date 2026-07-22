from typing import List

class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        
        def findBound(isFirst: bool) -> int:
            left, right = 0, len(nums) - 1
            bound = -1
            
            while left <= right:
                mid = (left + right) // 2
                
                if nums[mid] > target:
                    right = mid - 1
                elif nums[mid] < target:
                    left = mid + 1
                else:
                    # Target found, record it and keep searching
                    bound = mid
                    if isFirst:
                        # Narrow down to the left side to find the first occurrence
                        right = mid - 1
                    else:
                        # Narrow down to the right side to find the last occurrence
                        left = mid + 1
                        
            return bound

        first_pos = findBound(True)
        # If the first position isn't found, the target isn't in the array at all
        if first_pos == -1:
            return [-1, -1]
            
        last_pos = findBound(False)
        
        return [first_pos, last_pos]