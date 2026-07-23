import math

class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        numbers = list(range(1, n + 1))
        k -= 1  # Convert to 0-indexed
        permutation = []
        
        for i in range(n, 0, -1):
            fact = math.factorial(i - 1)
            index = k // fact
            permutation.append(str(numbers.pop(index)))
            k %= fact
            
        return "".join(permutation)