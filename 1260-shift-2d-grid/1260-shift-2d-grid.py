class Solution:
    def shiftGrid(self, grid: list[list[int]], k: int) -> list[list[int]]:
        m, n = len(grid), len(grid[0])
        total_elements = m * n
        
        # Optimize k to avoid unnecessary full rotations
        k = k % total_elements
        
        # If k is a multiple of total_elements, the grid remains unchanged
        if k == 0:
            return grid
            
        # Initialize an empty grid of the same dimensions
        ans = [[0] * n for _ in range(m)]
        
        for i in range(m):
            for j in range(n):
                # Calculate current 1D position
                curr_idx = i * n + j
                
                # Calculate new 1D position
                new_idx = (curr_idx + k) % total_elements
                
                # Convert back to 2D position
                new_r = new_idx // n
                new_c = new_idx % n
                
                # Place the element in the new grid
                ans[new_r][new_c] = grid[i][j]
                
        return ans