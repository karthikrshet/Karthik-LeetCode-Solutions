class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;
        long place = 1;
        
        // Traverse the number from right to left
        while (n > 0) {
            int digit = n % 10; // Extract the last digit
            
            // If it's a non-zero digit, add it to our new number 'x'
            if (digit != 0) {
                x = x + (digit * place);
                sum += digit;
                place *= 10; // Move to the next place value
            }
            
            n /= 10; // Remove the last digit from n
        }
        
        return x * sum;
    }
}