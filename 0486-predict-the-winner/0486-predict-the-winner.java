class Solution {
    public boolean predictTheWinner(int[] nums) { // Changed to lowercase 'p'
        int n = nums.length;
        Integer[][] memo = new Integer[n][n];
        return maxScoreDifference(nums, 0, n - 1, memo) >= 0;
    }
    
    private int maxScoreDifference(int[] nums, int left, int right, Integer[][] memo) {
        if (left == right) {
            return nums[left];
        }
        
        if (memo[left][right] != null) {
            return memo[left][right];
        }
        
        int pickLeft = nums[left] - maxScoreDifference(nums, left + 1, right, memo);
        int pickRight = nums[right] - maxScoreDifference(nums, left, right - 1, memo);
        
        memo[left][right] = Math.max(pickLeft, pickRight);
        return memo[left][right];
    }
}