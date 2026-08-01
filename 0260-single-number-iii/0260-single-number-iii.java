class Solution {
    public int[] singleNumber(int[] nums) {
        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }
        
        // Get the rightmost set bit (handles Integer.MIN_VALUE safely with bitwise AND)
        int diffBit = xorSum & -xorSum;
        
        int num1 = 0;
        int num2 = 0;
        
        for (int num : nums) {
            if ((num & diffBit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        
        return new int[] { num1, num2 };
    }
}