from typing import List

class Solution:
    def spiralOrder(self, matrix: List[List[str]]) -> List[int]:
        if not matrix or not matrix[0]:
            return []
            
        res = []
        top, bottom = 0, len(matrix) - 1
        left, right = 0, len(matrix[0]) - 1
        
        while top <= bottom and left <= right:
            # 1. Traverse Left to Right along the top row
            for col in range(left, right + 1):
                res.append(matrix[top][col])
            top += 1
            
            # 2. Traverse Top to Bottom along the right column
            for row in range(top, bottom + 1):
                res.append(matrix[row][right])
            right -= 1
            
            # 3. Traverse Right to Left along the bottom row (if still valid)
            if top <= bottom:
                for col in range(right, left - 1, -1):
                    res.append(matrix[bottom][col])
                bottom -= 1
                
            # 4. Traverse Bottom to Top along the left column (if still valid)
            if left <= right:
                for row in range(bottom, top - 1, -1):
                    res.append(matrix[row][left])
                left += 1
                
        return res