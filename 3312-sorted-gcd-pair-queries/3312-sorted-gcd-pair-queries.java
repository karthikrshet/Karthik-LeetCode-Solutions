class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Step 1: Find the maximum value in the array to limit our search space
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // Step 2: Track the frequency of each number in nums
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }
        
        // exactGcd[i] will store the EXACT number of pairs where GCD == i
        long[] exactGcd = new long[maxVal + 1];
        
        // Step 3: Iterate backwards from maxVal down to 1
        for (int i = maxVal; i >= 1; i--) {
            long countOfMultiples = 0;
            
            // Count how many numbers in the array are multiples of i
            for (int j = i; j <= maxVal; j += i) {
                countOfMultiples += freq[j];
            }
            
            // Calculate total possible pairs that are multiples of i
            long pairs = countOfMultiples * (countOfMultiples - 1) / 2;
            
            // Subtract pairs that have a strictly larger multiple as their actual GCD
            for (int j = 2 * i; j <= maxVal; j += i) {
                pairs -= exactGcd[j];
            }
            
            exactGcd[i] = pairs;
        }
        
        // Step 4: Create a prefix sum array to know the cumulative count of GCDs
        long[] prefix = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefix[i] = prefix[i - 1] + exactGcd[i];
        }
        
        // Step 5: Process each query using Binary Search
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i]; // The target index in the virtual sorted array
            
            int left = 1, right = maxVal;
            int res = 1;
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                
                // If the cumulative count at 'mid' is greater than our query index, 
                // the answer is at 'mid' or smaller.
                if (prefix[mid] > q) {
                    res = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = res;
        }
        
        return ans;
    }
}