class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[j] represents the number of distinct subsequences of s prefix that match t[0...j-1]
        // Using long to prevent any potential intermediate integer overflows during addition
        long[] dp = new long[n + 1];
        
        // An empty string t can always be formed by an empty subsequence 1 time
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            // Traverse backwards to use values from the previous row correctly
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return (int) dp[n];
    }
}