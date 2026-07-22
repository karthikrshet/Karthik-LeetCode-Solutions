from typing import List

class Solution:
    def trap(self, height: List[int]) -> int:
        stack = []  # Stores indices, not heights
        water_trapped = 0
        
        for current_index, current_height in enumerate(height):
            # Process basins while the current bar is taller than the stack's top
            while stack and current_height > height[stack[-1]]:
                # The 'dip' we are filling with water
                bottom_index = stack.pop()
                
                # If there's no left boundary, water spills out
                if not stack:
                    break
                
                # The left boundary is the new top of the stack
                left_index = stack[-1]
                
                # Calculate dimensions
                width = current_index - left_index - 1
                bounded_height = min(height[left_index], current_height) - height[bottom_index]
                
                water_trapped += width * bounded_height
                
            # Push the current index to maintain the decreasing stack
            stack.append(current_index)
            
        return water_trapped