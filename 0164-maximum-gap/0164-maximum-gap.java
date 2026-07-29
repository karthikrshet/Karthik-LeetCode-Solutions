import java.util.Arrays;

class Solution {
    public int maximumGap(int[] nums) {
        // Base case: less than 2 elements
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int n = nums.length;
        int min = nums[0];
        int max = nums[0];
        
        // Find the minimum and maximum elements in the array
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // If all elements are the same, the gap is 0
        if (min == max) {
            return 0;
        }

        // Calculate bucket size and number of buckets
        // Minimum possible max gap is ceiling of (max - min) / (n - 1)
        int bucketSize = Math.max(1, (max - min) / (n - 1));
        int bucketCount = (max - min) / bucketSize + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        
        // Initialize bucket limits
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // Put numbers into their corresponding buckets
        for (int num : nums) {
            int idx = (num - min) / bucketSize;
            bucketMin[idx] = Math.min(bucketMin[idx], num);
            bucketMax[idx] = Math.max(bucketMax[idx], num);
        }

        // Calculate the maximum gap
        int maxGap = 0;
        int prevMax = min;
        
        for (int i = 0; i < bucketCount; i++) {
            // Skip empty buckets
            if (bucketMin[i] == Integer.MAX_VALUE) {
                continue;
            }
            
            // The gap is the current bucket's min minus the previous bucket's max
            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }

        return maxGap;
    }
}