class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                // Minimum must be in the right half
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // Minimum must be in the left half (including mid)
                right = mid;
            } else {
                // nums[mid] == nums[right]
                // We can't be sure which half, but we can safely discard nums[right]
                right--;
            }
        }
        
        // When left == right, we have found the minimum element
        return nums[left];
    }
}