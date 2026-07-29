class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        // dp[j] will store the minimum health required to reach the princess 
        // starting from the current row at column j.
        int[] dp = new int[n + 1];
        
        // Initialize the array with MAX_VALUE to represent out-of-bounds walls
        for (int j = 0; j <= n; j++) {
            dp[j] = Integer.MAX_VALUE;
        }
        
        // Base condition: The knight needs at least 1 health to survive 
        // after reaching the destination (simulating a phantom cell to the right/bottom)
        dp[n - 1] = 1;
        
        // Traverse backwards from bottom-right to top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // The minimum health required upon exiting this cell
                int minHealthOnExit = Math.min(dp[j], dp[j + 1]);
                
                // The minimum health required before entering this cell
                dp[j] = Math.max(1, minHealthOnExit - dungeon[i][j]);
            }
        }
        
        // The first element now contains the minimum initial health needed at (0,0)
        return dp[0];
    }
}