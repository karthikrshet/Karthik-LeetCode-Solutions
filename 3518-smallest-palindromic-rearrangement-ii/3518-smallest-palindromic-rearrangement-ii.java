import java.util.*;

class Solution {
    private long nCr(int n, int r, long CAP) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n - r) r = n - r;
        long res = 1;
        for (int i = 1; i <= r; ++i) {
            res = res * (n - i + 1) / i;
            if (res >= CAP) return CAP;
        }
        return res;
    }

    private long getMultinomial(int[] cnts, long CAP) {
        int rem = 0;
        for (int c : cnts) rem += c;
        long res = 1;
        for (int c : cnts) {
            if (c > 0) {
                long ways = nCr(rem, c, CAP);
                res *= ways;
                if (res >= CAP) return CAP;
                rem -= c;
            }
        }
        return res;
    }

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        String mid = "";
        int[] halfCnt = new int[26];
        int halfLen = 0;

        for (int i = 0; i < 26; ++i) {
            if (freq[i] % 2 != 0) {
                mid = String.valueOf((char)('a' + i));
            }
            halfCnt[i] = freq[i] / 2;
            halfLen += halfCnt[i];
        }

        long CAP = (long)k + 1;
        long totalWays = getMultinomial(halfCnt, CAP);
        if (totalWays < k) return "";

        StringBuilder firstHalf = new StringBuilder();
        for (int i = 0; i < halfLen; ++i) {
            for (int c = 0; c < 26; ++c) {
                if (halfCnt[c] > 0) {
                    halfCnt[c]--;
                    long ways = getMultinomial(halfCnt, CAP);
                    if (k <= ways) {
                        firstHalf.append((char)('a' + c));
                        break;
                    } else {
                        k -= ways;
                        halfCnt[c]++;
                    }
                }
            }
        }

        String secondHalf = new StringBuilder(firstHalf).reverse().toString();
        return firstHalf.toString() + mid + secondHalf;
    }
}