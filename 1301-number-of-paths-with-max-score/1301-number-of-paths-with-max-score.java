import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
        
        // dp[r][c] stores the max score to reach cell (r, c)
        int[][] dp = new int[n][n];
        // ways[r][c] stores the number of paths to reach (r, c) with the max score
        int[][] ways = new int[n][n];
        
        // Starting point at the bottom right
        ways[n - 1][n - 1] = 1;
        
        // To compute a cell, we look at where we could have come from:
        // Down, Right, and Down-Right
        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
        
        // Iterate from bottom-right to top-left
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                // Skip the starting square as it's already initialized
                if (r == n - 1 && c == n - 1) continue;
                
                char ch = board.get(r).charAt(c);
                
                // Obstacles cannot be entered
                if (ch == 'X') continue;
                
                int maxPrevScore = -1;
                int pathsCount = 0;
                
                // Check the three possible preceding cells
                for (int[] dir : dirs) {
                    int prevR = r + dir[0];
                    int prevC = c + dir[1];
                    
                    // If the previous cell is within bounds and reachable
                    if (prevR < n && prevC < n && ways[prevR][prevC] > 0) {
                        if (dp[prevR][prevC] > maxPrevScore) {
                            // Found a strictly better path, reset count
                            maxPrevScore = dp[prevR][prevC];
                            pathsCount = ways[prevR][prevC];
                        } else if (dp[prevR][prevC] == maxPrevScore) {
                            // Found another path with the same max score, add to count
                            pathsCount = (pathsCount + ways[prevR][prevC]) % MOD;
                        }
                    }
                }
                
                // If this cell is reachable from at least one valid path
                if (pathsCount > 0) {
                    int cellValue = (ch == 'E') ? 0 : (ch - '0');
                    dp[r][c] = maxPrevScore + cellValue;
                    ways[r][c] = pathsCount;
                }
            }
        }
        
        // Return the max score and number of ways at the destination (top-left)
        return new int[]{dp[0][0], ways[0][0]};
    }
}