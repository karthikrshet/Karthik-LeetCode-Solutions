
class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Case 1: k = 1. Find the maximum element that appears exactly once.
        if (k == 1) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int num : nums) {
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            }
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() == 1) {
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }
        
        // Case 2: k = n. The entire array is the only subarray of size k.
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }
        
        // Case 3: 1 < k < n. 
        // Only elements at the boundaries (nums[0] and nums[n-1]) can appear in exactly one subarray 
        // of size k, provided they do not appear anywhere else in the array.
        int ans = -1;
        
        // Check nums[0]
        if (appearsOnlyOnce(nums, nums[0])) {
            ans = Math.max(ans, nums[0]);
        }
        
        // Check nums[n - 1]
        if (appearsOnlyOnce(nums, nums[n - 1])) {
            ans = Math.max(ans, nums[n - 1]);
        }
        
        return ans;
    }
    
    private boolean appearsOnlyOnce(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) {
                count++;
            }
        }
        return count == 1;
    }
}