class Solution {
    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0;
        
        // Move all non-zero elements to the front of the array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != lastNonZeroFoundAt) {
                    int temp = nums[lastNonZeroFoundAt];
                    nums[lastNonZeroFoundAt] = nums[i];
                    nums[i] = temp;
                }
                lastNonZeroFoundAt++;
            }
        }
    }
}