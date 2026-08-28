import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        int midChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = i;
            }
        }

        if (oddCount > (n % 2 == 1 ? 1 : 0)) {
            return "";
        }

        int halfLen = n / 2;
        char[] half = new char[halfLen];
        StringBuilder result = new StringBuilder();

        if (backtrack(0, half, count, target, n, midChar, result, false)) {
            return result.toString();
        }

        return "";
    }

    private boolean backtrack(int idx, char[] half, int[] count, String target, int n, int midChar, StringBuilder finalRes, boolean isGreater) {
        if (idx == half.length) {
            StringBuilder sb = new StringBuilder();
            for (char c : half) sb.append(c);
            String left = sb.toString();
            String mid = (n % 2 == 1) ? String.valueOf((char) ('a' + midChar)) : "";
            String right = sb.reverse().toString();
            sb.reverse(); // restore
            String candidate = left + mid + right;

            if (candidate.compareTo(target) > 0) {
                finalRes.append(candidate);
                return true;
            }
            return false;
        }

        // Optimization: iterate only through available characters
        for (int i = 0; i < 26; i++) {
            if (count[i] > 1) {
                char c = (char) ('a' + i);
                
                // If we haven't exceeded target yet, check if current choice is valid against target prefix
                if (!isGreater && c < target.charAt(idx)) {
                    continue;
                }

                half[idx] = c;
                count[i] -= 2;
                
                boolean nextIsGreater = isGreater || (c > target.charAt(idx));
                
                if (backtrack(idx + 1, half, count, target, n, midChar, finalRes, nextIsGreater)) {
                    return true;
                }
                
                count[i] += 2;
            }
        }
        return false;
    }
}