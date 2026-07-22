from typing import List

class Solution:
    def jump(self, nums: List[int]) -> int:
        jumps = 0
        current_jump_end = 0
        farthest = 0
        
        # We stop at len(nums) - 1 because we don't need to jump from the last index.
        for i in range(len(nums) - 1):
            # Update the farthest reachable index from the current position
            farthest = max(farthest, i + nums[i])
            
            # If we've reached the boundary of the current jump window
            if i == current_jump_end:
                jumps += 1               # We must make a jump now
                current_jump_end = farthest # The new boundary is the farthest we can reach
                
        return jumps