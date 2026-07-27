class Solution {
    public int minCut(String s) {
        int n = s.length();
        if (n <= 1) {
            return 0;
        }
        
        int[] cuts = new int[n];
        boolean[][] isPalindrome = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            int minCuts = i; // Max cuts possible is i (all single characters)
            
            for (int j = 0; j <= i; j++) {
                // Check if s[j...i] is a palindrome
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || isPalindrome[j + 1][i - 1])) {
                    isPalindrome[j][i] = true;
                    
                    if (j == 0) {
                        minCuts = 0; // The whole prefix s[0...i] is a palindrome
                    } else {
                        minCuts = Math.min(minCuts, cuts[j - 1] + 1);
                    }
                }
            }
            cuts[i] = minCuts;
        }
        
        return cuts[n - 1];
    }
}