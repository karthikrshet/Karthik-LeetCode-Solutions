class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) {
            return 0;
        }
        
        int n = prices.length;
        
        // If k is large enough, treat it as unlimited transactions
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }
        
        // dp[i][j] represents max profit on day i with at most j transactions
        int[][] dp = new int[k + 1][n];
        
        for (int j = 1; j <= k; j++) {
            int maxDiff = -prices[0];
            for (int i = 1; i < n; i++) {
                dp[j][i] = Math.max(dp[j][i - 1], prices[i] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[j - 1][i] - prices[i]);
            }
        }
        
        return dp[k][n - 1];
    }
}