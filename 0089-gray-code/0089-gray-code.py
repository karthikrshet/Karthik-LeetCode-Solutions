from typing import List

class Solution:
    def grayCode(self, n: int) -> List[int]:
        # There are 2^n numbers in an n-bit Gray code sequence
        total_numbers = 1 << n
        result = []
        
        for i in range(total_numbers):
            # Apply the Gray code mathematical formula: i XOR (i >> 1)
            result.append(i ^ (i >> 1))
            
        return result