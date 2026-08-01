class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        // Iterate through each column of the submatrix
        for (int j = y; j < y + k; j++) {
            // Swap rows from top to bottom for the upper half of the square
            for (int i = 0; i < k / 2; i++) {
                int topRow = x + i;
                int bottomRow = x + k - 1 - i;
                
                // Swap the values
                int temp = grid[topRow][j];
                grid[topRow][j] = grid[bottomRow][j];
                grid[bottomRow][j] = temp;
            }
        }
        return grid;
    }
}