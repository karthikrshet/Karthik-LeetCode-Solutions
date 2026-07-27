class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int totalProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // Capture every positive price increase
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        
        return totalProfit;
    }
}