from typing import List

class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        n = len(matrix)
        
        # Step 1: Transpose the matrix (swap across the main diagonal)
        for i in range(n):
            # Start j from i to avoid swapping elements back to their original positions
            for j in range(i, n):
                matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
                
        # Step 2: Reverse each row
        for i in range(n):
            # Use two pointers to reverse the row in-place
            left, right = 0, n - 1
            while left < right:
                matrix[i][left], matrix[i][right] = matrix[i][right], matrix[i][left]
                left += 1
                right -= 1
                
        # Alternatively, in Python, Step 2 can be done as:
        # for i in range(n):
        #     matrix[i].reverse()