import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        
        // Dictionary to keep track of counts of characters in t
        Map<Character, Integer> dictT = new HashMap<>();
        for (char c : t.toCharArray()) {
            dictT.put(c, dictT.getOrDefault(c, 0) + 1);
        }
        
        int required = dictT.size();
        int formed = 0;
        
        // Window frequency map
        Map<Character, Integer> windowCounts = new HashMap<>();
        
        // Pointers: [min_len, left_idx, right_idx]
        int[] ans = {-1, 0, 0};
        int l = 0, r = 0;
        
        while (r < s.length()) {
            char c = s.charAt(r);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
            
            // If the current character's frequency matches its target in t, increment formed
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }
            
            // Try and contract the window until it ceases to be 'desirable'
            while (l <= r && formed == required) {
                char leftChar = s.charAt(l);
                
                // Save the smallest window
                if (ans[0] == -1 || (r - l + 1) < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                
                // The character at the position pointed by the `left` pointer is about to be excluded
                windowCounts.put(leftChar, windowCounts.get(leftChar) - 1);
                if (dictT.containsKey(leftChar) && windowCounts.get(leftChar).intValue() < dictT.get(leftChar).intValue()) {
                    formed--;
                }
                
                l++;
            }
            
            r++;
        }
        
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}