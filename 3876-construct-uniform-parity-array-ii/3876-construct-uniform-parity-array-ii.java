class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        
        // Find the minimum odd number in nums1
        for (int x : nums1) {
            if (x % 2 != 0) {
                minOdd = Math.min(minOdd, x);
            }
        }
        
        // Check if any even number is smaller than the minimum odd number
        for (int x : nums1) {
            if (x % 2 == 0 && minOdd != Integer.MAX_VALUE && x < minOdd) {
                return false;
            }
        }
        
        return true;
    }
}