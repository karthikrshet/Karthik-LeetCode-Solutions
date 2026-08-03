class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] represents the max score difference starting from index i
        int[] dp = new int[n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            int maxDiff = Integer.MIN_VALUE;
            int sum = 0;
            
            // Try taking 1, 2, or 3 stones
            for (int k = 1; k <= 3 && i + k <= n; k++) {
                sum += stoneValue[i + k - 1];
                maxDiff = Math.max(maxDiff, sum - dp[i + k]);
            }
            
            dp[i] = maxDiff;
        }
        
        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}