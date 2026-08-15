class Solution {
    public boolean isPowerOfThree(int n) {
        // 1162261467 is 3^19, the largest power of 3 in a 32-bit signed integer
        return n > 0 && 1162261467 % n == 0;
    }
}