class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert list to set for O(1) lookups
        Set<String> dict = new HashSet<>(wordDict);
        
        // Find the max length of a dictionary word for our optimization
        int maxLength = 0;
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
        }
        
        // dp[i] represents whether s.substring(0, i) can be segmented
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // Base case
        
        for (int i = 1; i <= s.length(); i++) {
            // Check previous valid states, bounding our look-back by maxLength
            for (int j = i - 1; j >= Math.max(0, i - maxLength); j--) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // Once we find a valid split for i, we can stop looking back
                }
            }
        }
        
        return dp[s.length()];
    }
}