class Solution {
    public int maximumLengthSubstring(String s) {
        int[] cnt = new int[26];
        int ans = 0;
        
        for (int i = 0, j = 0; j < s.length(); ++j) {
            int idx = s.charAt(j) - 'a';
            ++cnt[idx];
            
            // If any character occurs more than 2 times, shrink the window from the left
            while (cnt[idx] > 2) {
                --cnt[s.charAt(i++) - 'a'];
            }
            
            // Update the maximum length found so far
            ans = Math.max(ans, j - i + 1);
        }
        
        return ans;
    }
}