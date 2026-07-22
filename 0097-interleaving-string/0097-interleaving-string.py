class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        m, n = len(s1), len(s2)
        
        # Base check: total lengths must match
        if m + n != len(s3):
            return False
            
        # dp[j] keeps track of whether s1[:i] and s2[:j] can form s3[:i+j]
        dp = [False] * (n + 1)
        dp[0] = True
        
        # Initialize the first row (Using 0 characters from s1)
        for j in range(1, n + 1):
            dp[j] = dp[j - 1] and s2[j - 1] == s3[j - 1]
            
        # Fill the DP table row by row
        for i in range(1, m + 1):
            # Update the 0th column (Using 0 characters from s2)
            dp[0] = dp[0] and s1[i - 1] == s3[i - 1]
            
            for j in range(1, n + 1):
                # Two possibilities:
                # 1. Take character from s1 (dp[j] from previous row)
                # 2. Take character from s2 (dp[j-1] from current row)
                match_s1 = dp[j] and s1[i - 1] == s3[i + j - 1]
                match_s2 = dp[j - 1] and s2[j - 1] == s3[i + j - 1]
                
                dp[j] = match_s1 or match_s2
                
        return dp[n]