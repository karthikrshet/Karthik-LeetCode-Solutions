class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int high = nums.length - 1;
        
        while (left <= high) {
            int mid = left + (high - left) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            // Handle duplicates where we cannot determine the sorted half
            if (nums[left] == nums[mid] && nums[mid] == nums[high]) {
                left++;
                high--;
            } 
            // Check if the left half is sorted
            else if (nums[left] <= nums[mid]) {
                // Check if target lies within the sorted left half
                if (nums[left] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    left = mid + 1;
                }
            } 
            // Otherwise, the right half must be sorted
            else {
                // Check if target lies within the sorted right half
                if (nums[mid] < target && target <= nums[high]) {
                    left = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        
        return false;
    }
}