class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        
        int k = 2; // The first two elements are always valid
        
        for (int i = 2; i < nums.length; i++) {
            // If the current element is different from the element two positions back, it's valid
            if (nums[i] != nums[k - 2]) {
                nums[k] = nums[i];
                k++;
            }
        }
        
        return k;
    }
}