class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        long MOD = 1_000_000_007;
        
        // dp[i][j][0]: number of stable arrays with i zeros and j ones, ending in 0
        // dp[i][j][1]: number of stable arrays with i zeros and j ones, ending in 1
        long[][][] dp = new long[zero + 1][one + 1][2];
        
        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        for (int j = 1; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }
        
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                // Ending in 0: Can append 0 to arrays ending in 0 or 1 from (i-1, j)
                // We must subtract cases where we exceed 'limit' consecutive zeros.
                long ways0 = dp[i - 1][j][0] + dp[i - 1][j][1];
                if (i > limit) {
                    ways0 = (ways0 - dp[i - limit - 1][j][1] + MOD) % MOD;
                }
                dp[i][j][0] = ways0 % MOD;
                
                // Ending in 1: Can append 1 to arrays ending in 0 or 1 from (i, j-1)
                // We must subtract cases where we exceed 'limit' consecutive ones.
                long ways1 = dp[i][j - 1][0] + dp[i][j - 1][1];
                if (j > limit) {
                    ways1 = (ways1 - dp[i][j - limit - 1][0] + MOD) % MOD;
                }
                dp[i][j][1] = ways1 % MOD;
            }
        }
        
        long ans = (dp[zero][one][0] + dp[zero][one][1]) % MOD;
        return (int) ans;
    }
}