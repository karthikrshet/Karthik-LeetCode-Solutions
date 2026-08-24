class Solution:
    def stoneGameVIII(self, stones: list[int]) -> int:
        n = len(stones)
        # Compute prefix sums
        pref = list(stones)
        for i in range(1, n):
            pref[i] += pref[i - 1]
            
        # Base case: if we are at the last choice, we take all remaining stones (prefix sum up to n-1)
        res = pref[-1]
        
        # Iterate backwards from n-2 down to 1
        for i in range(n - 2, 0, -1):
            res = max(res, pref[i] - res)
            
        return res