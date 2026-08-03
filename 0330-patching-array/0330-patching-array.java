class Solution {
    public int minPatches(int[] nums, int n) {
        long miss = 1; // The smallest sum that cannot be formed yet
        int patches = 0;
        int i = 0;
        int len = nums.length;
        
        while (miss <= n) {
            if (i < len && nums[i] <= miss) {
                miss += nums[i];
                i++;
            } else {
                // We need to patch the array with 'miss'
                miss += miss;
                patches++;
            }
        }
        
        return patches;
    }
}