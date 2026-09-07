class Solution:
    def distinctSubseqII(self, s: str) -> int:
        MOD = 10**9 + 7
        dp = {}
        total = 0
        
        for c in s:
            new_count = (total + 1) % MOD
            old_count = dp.get(c, 0)
            total = (total - old_count + new_count) % MOD
            dp[c] = new_count
            
        return total