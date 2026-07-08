class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long MOD = 1_000_000_007;

        // Step 1: Precompute powers of 10 modulo 10^9 + 7
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Step 2: Build prefix arrays (1-indexed for easier boundary handling)
        int[] count = new int[n + 1]; // Tracks number of non-zero digits
        long[] pref = new long[n + 1]; // Tracks the concatenated value modulo MOD
        long[] sum = new long[n + 1];  // Tracks the sum of the digits

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            
            // Carry over previous values by default
            count[i + 1] = count[i];
            pref[i + 1] = pref[i];
            sum[i + 1] = sum[i];

            // If we hit a non-zero digit, update our running totals
            if (digit > 0) {
                count[i + 1]++;
                pref[i + 1] = (pref[i] * 10 + digit) % MOD;
                sum[i + 1] = sum[i] + digit;
            }
        }

        // Step 3: Process each query in O(1) time
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int L = queries[i][0];
            int R = queries[i][1];

            // How many non-zero digits are in this specific substring?
            int k = count[R + 1] - count[L];
            
            // What is the sum of those digits?
            long currentSum = sum[R + 1] - sum[L];
            
            // The Magic Math: Extract the substring's concatenated value
            // (pref[R + 1] - pref[L] * 10^k) % MOD
            long x = (pref[R + 1] - (pref[L] * pow10[k]) % MOD + MOD) % MOD;
            
            // Multiply and store the final answer
            result[i] = (int) ((x * (currentSum % MOD)) % MOD);
        }
        
        return result;
    }
}