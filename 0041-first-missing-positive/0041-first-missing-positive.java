class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: Place every positive integer (up to n) at its correct index.
        // For example, the number 1 goes to index 0, number 2 goes to index 1, etc.
        for (int i = 0; i < n; i++) {
            // Check if the number is in our target range [1, n] 
            // AND if it is not already sitting at its correct target index.
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // Swap the current number to its rightful place
                int targetIndex = nums[i] - 1;
                int temp = nums[targetIndex];
                nums[targetIndex] = nums[i];
                nums[i] = temp;
            }
        }

        // Step 2: Scan the array to find the first index that doesn't match
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1; // We found the missing number!
            }
        }

        // Step 3: If all numbers from 1 to n are perfectly placed, 
        // the missing positive is exactly n + 1.
        return n + 1;
    }
}