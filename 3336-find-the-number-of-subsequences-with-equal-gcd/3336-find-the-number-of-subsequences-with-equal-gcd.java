class Solution {
    public int subsequencePairCount(int[] nums) {
        int MOD = 1000000007;
        
        // Find the maximum value to bound our DP array size
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        // dp[g1][g2] stores the number of valid disjoint subsequences
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1; // Base state: both subsequences are empty

        for (int x : nums) {
            // Use a temporary DP array to represent the next state and prevent double-counting
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] > 0) {
                        int count = dp[g1][g2];

                        // Option 1: Skip the current element (add to neither)
                        nextDp[g1][g2] = (nextDp[g1][g2] + count) % MOD;

                        // Option 2: Add the current element to the first subsequence
                        int ng1 = (g1 == 0) ? x : gcd(g1, x);
                        nextDp[ng1][g2] = (nextDp[ng1][g2] + count) % MOD;

                        // Option 3: Add the current element to the second subsequence
                        int ng2 = (g2 == 0) ? x : gcd(g2, x);
                        nextDp[g1][ng2] = (nextDp[g1][ng2] + count) % MOD;
                    }
                }
            }
            dp = nextDp; // Move to the next sequence step
        }

        // Sum up all valid pairs where both subsequences are non-empty and have equal GCDs
        int ans = 0;
        for (int g = 1; g <= maxVal; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        
        return ans;
    }

    // Helper method to compute the Greatest Common Divisor
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}