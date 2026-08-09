
class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        int[][] memo = new int[n][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        return dp(piles, suffixSum, 0, 1, memo);
    }
    
    private int dp(int[] piles, int[] suffixSum, int i, int m, int[][] memo) {
        int n = piles.length;
        if (i + 2 * m >= n) {
            return suffixSum[i];
        }
        if (memo[i][m] != -1) {
            return memo[i][m];
        }
        int maxStones = 0;
        for (int x = 1; x <= 2 * m; x++) {
            int opponentStones = dp(piles, suffixSum, i + x, Math.max(m, x), memo);
            int currentStones = suffixSum[i] - opponentStones;
            maxStones = Math.max(maxStones, currentStones);
        }
        
        
        return memo[i][m] = maxStones;
    }
}