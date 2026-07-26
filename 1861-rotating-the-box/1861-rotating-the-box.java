class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        char[][] res = new char[n][m];
        
        for (int i = 0; i < m; i++) {
            int dropPos = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (boxGrid[i][j] == '*') {
                    dropPos = j - 1;
                } else if (boxGrid[i][j] == '#') {
                    // Temporarily place the stone at the current gravity-adjusted position
                    boxGrid[i][j] = '.';
                    boxGrid[i][dropPos] = '#';
                    dropPos--;
                }
            }
        }
        
        // Rotate 90 degrees clockwise into the result matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[j][m - 1 - i] = boxGrid[i][j];
            }
        }
        
        return res;
    }
}