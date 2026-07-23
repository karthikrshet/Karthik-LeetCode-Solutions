import math

class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        # Combinatorics formula: C(m + n - 2, m - 1)
        return math.comb(m + n - 2, m - 1)