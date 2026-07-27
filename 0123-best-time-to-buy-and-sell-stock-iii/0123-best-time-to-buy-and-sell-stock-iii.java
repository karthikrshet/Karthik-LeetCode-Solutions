class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int t1Hold = -prices[0];
        int t1Release = 0;
        int t2Hold = -prices[0];
        int t2Release = 0;
        
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            
            t1Hold = Math.max(t1Hold, -price);
            t1Release = Math.max(t1Release, t1Hold + price);
            t2Hold = Math.max(t2Hold, t1Release - price);
            t2Release = Math.max(t2Release, t2Hold + price);
        }
        
        return t2Release;
    }
}