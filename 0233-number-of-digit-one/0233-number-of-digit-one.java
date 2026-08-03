class Solution {
    public int countDigitOne(int n) {
        int count = 0;
        long i = 1; // Current digit place (1, 10, 100, ...)
        
        while (i <= n) {
            long divider = i * 10;
            long high = n / divider;
            long curr = (n / i) % 10;
            long low = n % i;
            
            if (curr == 0) {
                count += high * i;
            } else if (curr == 1) {
                count += high * i + low + 1;
            } else {
                count += (high + 1) * i;
            }
            
            i = divider;
        }
        
        return count;
    }
}