class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        
        // XOR all elements together
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        
        return result;
    }
}