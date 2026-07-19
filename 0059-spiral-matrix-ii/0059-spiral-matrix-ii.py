from typing import List

class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        # Step 1: Initialize the n x n matrix with zeros
        matrix = [[0] * n for _ in range(n)]
        
        # Step 2: Define the boundaries
        top, bottom = 0, n - 1
        left, right = 0, n - 1
        
        num = 1
        
        # Step 3: Traverse and fill the matrix
        while left <= right and top <= bottom:
            
            # 1. Traverse from Left to Right along the top boundary
            for i in range(left, right + 1):
                matrix[top][i] = num
                num += 1
            top += 1
            
            # 2. Traverse from Top to Bottom along the right boundary
            for i in range(top, bottom + 1):
                matrix[i][right] = num
                num += 1
            right -= 1
            
            # 3. Traverse from Right to Left along the bottom boundary
            # (Check is necessary for non-square matrices, though n x n guarantees this is safe)
            if top <= bottom:
                for i in range(right, left - 1, -1):
                    matrix[bottom][i] = num
                    num += 1
                bottom -= 1
                
            # 4. Traverse from Bottom to Top along the left boundary
            if left <= right:
                for i in range(bottom, top - 1, -1):
                    matrix[i][left] = num
                    num += 1
                left += 1
                
        return matrix