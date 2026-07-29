class Solution {
    public int trailingZeroes(int n) {
        int zeroCount = 0;
        
        // Repeatedly divide n by 5 and accumulate the quotients
        while (n > 0) {
            n /= 5;
            zeroCount += n;
        }
        
        return zeroCount;
    }
}