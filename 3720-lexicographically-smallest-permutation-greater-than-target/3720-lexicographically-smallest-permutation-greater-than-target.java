import java.util.*;

class Solution {
    private int[] count = new int[26];
    private char[] targetChars;
    private int n;
    private List<Character> sortedChars;
    
    public String lexGreaterPermutation(String s, String target) {
        n = s.length();
        Arrays.fill(count, 0);
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        sortedChars = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                sortedChars.add((char) ('a' + i));
            }
        }
        
        targetChars = target.toCharArray();
        StringBuilder currentPrefix = new StringBuilder();
        
        // We start with restricted = true, but to be strictly greater, 
        // we eventually *must* make a choice strictly greater than target at some point.
        if (canForm(0, true, false, currentPrefix)) {
            return currentPrefix.toString();
        }
        return "";
    }
    
    private boolean canForm(int idx, boolean restricted, boolean isGreater, StringBuilder currentPrefix) {
        if (idx == n) {
            // At the end, we must have made at least one strictly greater choice
            return isGreater;
        }
        
        char startChar = restricted ? targetChars[idx] : 'a';
        
        for (char ch : sortedChars) {
            int cIdx = ch - 'a';
            if (count[cIdx] > 0) {
                if (restricted && ch < startChar) {
                    continue;
                }
                
                // Choose
                count[cIdx]--;
                currentPrefix.append(ch);
                
                boolean nextRestricted = restricted && (ch == startChar);
                boolean nextIsGreater = isGreater || (ch > startChar);
                
                if (canForm(idx + 1, nextRestricted, nextIsGreater, currentPrefix)) {
                    return true;
                }
                
                // Backtrack (Unchoose)
                currentPrefix.deleteCharAt(currentPrefix.length() - 1);
                count[cIdx]++;
            }
        }
        
        return false;
    }
}