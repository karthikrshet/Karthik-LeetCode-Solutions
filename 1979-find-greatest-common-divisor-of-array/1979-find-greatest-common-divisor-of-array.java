class Solution {
    public int findGCD(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        
        // Step 1: Find the minimum and maximum values in one pass
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        
        // Step 2: Return the GCD of min and max
        return calculateGCD(min, max);
    }
    
    // Helper method to compute GCD using the Euclidean Algorithm
    private int calculateGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        // Recursively call with (b, remainder of a divided by b)
        return calculateGCD(b, a % b);
    }
}