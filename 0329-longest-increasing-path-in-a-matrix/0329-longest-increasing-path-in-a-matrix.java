class Solution {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int maxLen = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxLen = Math.max(maxLen, dfs(matrix, i, j, memo, m, n));
            }
        }
        
        return maxLen;
    }
    
    private int dfs(int[][] matrix, int r, int c, int[][] memo, int m, int n) {
        if (memo[r][c] != 0) {
            return memo[r][c];
        }
        
        int pathLen = 1; // At least the cell itself
        
        for (int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && matrix[nr][nc] > matrix[r][c]) {
                pathLen = Math.max(pathLen, 1 + dfs(matrix, nr, nc, memo, m, n));
            }
        }
        
        memo[r][c] = pathLen;
        return pathLen;
    }
}