class Solution {
    public int maxProduct(int n) {
        int maxDigit = 0;
        int secondMaxDigit = 0;
        
        while (n > 0) {
            int digit = n % 10;
            if (digit > maxDigit) {
                secondMaxDigit = maxDigit;
                maxDigit = digit;
            } else if (digit > secondMaxDigit) {
                secondMaxDigit = digit;
            }
            n /= 10;
        }
        
        return maxDigit * secondMaxDigit;
    }
}