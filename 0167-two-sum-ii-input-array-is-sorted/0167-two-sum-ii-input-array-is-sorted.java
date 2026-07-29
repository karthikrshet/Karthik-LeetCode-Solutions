class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            
            if (sum == target) {
                // The problem asks for 1-based indices, so we add 1
                return new int[] {left + 1, right + 1};
            } else if (sum < target) {
                // We need a larger sum, move the left pointer up
                left++;
            } else {
                // We need a smaller sum, move the right pointer down
                right--;
            }
        }
        
        // Return an empty array if no solution is found 
        // (though the problem guarantees exactly one solution)
        return new int[2];
    }
}