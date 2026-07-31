class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;
        
        for (int num : nums) {
            // Update 'ones' with the new number, unless the bit is already in 'twos'
            ones = (ones ^ num) & ~twos;
            
            // Update 'twos' with the new number, unless the bit is already in 'ones'
            twos = (twos ^ num) & ~ones;
        }
        
        // The single number that appeared once will be stored in 'ones'
        return ones;
    }
}