import java.util.Arrays;

class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        
        // Every child must have at least one candy
        Arrays.fill(candies, 1);
        
        // Pass 1: Compare with left neighbor
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        
        // Pass 2: Compare with right neighbor and accumulate sum
        // Initialize sum with the last element since the loop won't cover it
        int totalCandies = candies[n - 1]; 
        
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                // Take the max to satisfy both left and right neighbor constraints
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            totalCandies += candies[i];
        }
        
        return totalCandies;
    }
}