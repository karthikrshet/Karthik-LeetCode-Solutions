class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // dp[i][j] represents the minimum edit distance between 
        // word1[0...i-1] and word2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Base case: word1 is empty, insert all characters of word2
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        // Base case: word2 is empty, delete all characters of word1
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match, no operation is needed
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Take the minimum of Replace, Delete, or Insert, plus 1
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], 
                                   Math.min(dp[i - 1][j], 
                                            dp[i][j - 1]));
                }
            }
        }
        
        return dp[m][n];
    }
}