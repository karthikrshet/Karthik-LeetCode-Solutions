from typing import List

class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])
        
        # 1. Accumulate sums for the first row (can only come from the left)
        for j in range(1, n):
            grid[0][j] += grid[0][j-1]
            
        # 2. Accumulate sums for the first column (can only come from above)
        for i in range(1, m):
            grid[i][0] += grid[i-1][0]
            
        # 3. Process the rest of the grid using the DP transition
        for i in range(1, m):
            for j in range(1, n):
                # The new cost is the current cell's value + the cheaper path to get there
                grid[i][j] += min(grid[i-1][j], grid[i][j-1])
                
        # The bottom-right corner now holds the minimum path sum for the whole grid
        return grid[-1][-1]