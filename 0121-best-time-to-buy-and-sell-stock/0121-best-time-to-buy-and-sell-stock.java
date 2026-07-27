class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i]; // Found a lower buying price
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice); // Check potential profit
            }
        }
        
        return maxProfit;
    }
}