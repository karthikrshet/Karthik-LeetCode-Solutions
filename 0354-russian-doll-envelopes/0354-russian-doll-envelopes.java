import java.util.Arrays;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;

        // Sort by width ascending; if widths are equal, sort by height descending
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        // Extract the heights and find LIS using binary search
        int[] dp = new int[envelopes.length];
        int len = 0;

        for (int[] envelope : envelopes) {
            int height = envelope[1];
            int idx = Arrays.binarySearch(dp, 0, len, height);
            
            if (idx < 0) {
                idx = -(idx + 1);
            }
            
            dp[idx] = height;
            if (idx == len) {
                len++;
            }
        }

        return len;
    }
}