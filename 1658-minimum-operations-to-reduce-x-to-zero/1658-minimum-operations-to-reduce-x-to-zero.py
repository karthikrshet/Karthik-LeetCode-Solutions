class Solution:
    def minOperations(self, nums: list[int], x: int) -> int:
        target = sum(nums) - x
        
        # If target is less than 0, x is greater than the total sum of the array
        if target < 0:
            return -1
        
        # If target is 0, we need to remove all elements
        if target == 0:
            return len(nums)
        
        max_len = -1
        current_sum = 0
        left = 0
        
        for right in range(len(nums)):
            current_sum += nums[right]
            
            # Shrink the window from the left if current_sum exceeds target
            while current_sum > target:
                current_sum -= nums[left]
                left += 1
                
            # If we found a valid subarray with the exact target sum
            if current_sum == target:
                max_len = max(max_len, right - left + 1)
                
        return len(nums) - max_len if max_len != -1 else -1