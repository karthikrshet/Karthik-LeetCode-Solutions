from typing import List

class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        if not matrix or not matrix[0]:
            return False
            
        m = len(matrix)
        n = len(matrix[0])
        
        # Treat the matrix as a 1D array
        left = 0
        right = m * n - 1
        
        while left <= right:
            mid = (left + right) // 2
            
            # Convert 1D mid index to 2D matrix coordinates
            mid_value = matrix[mid // n][mid % n]
            
            if mid_value == target:
                return True
            elif mid_value < target:
                # Target is in the right half
                left = mid + 1
            else:
                # Target is in the left half
                right = mid - 1
                
        return False