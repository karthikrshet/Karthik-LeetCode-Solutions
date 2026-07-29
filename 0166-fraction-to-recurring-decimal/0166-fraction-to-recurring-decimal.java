import java.util.HashMap;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // Base case: zero numerator
        if (numerator == 0) {
            return "0";
        }
        
        StringBuilder result = new StringBuilder();
        
        // Determine the sign using XOR
        if (numerator < 0 ^ denominator < 0) {
            result.append("-");
        }
        
        // Convert to long to avoid integer overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        
        // Append the integral part
        result.append(num / den);
        
        long remainder = num % den;
        if (remainder == 0) {
            return result.toString();
        }
        
        // Append the decimal point
        result.append(".");
        
        // Map to store remainders and their corresponding index in the string
        HashMap<Long, Integer> remainderMap = new HashMap<>();
        
        // Simulate long division
        while (remainder != 0) {
            if (remainderMap.containsKey(remainder)) {
                // Cycle found, insert parentheses and break
                result.insert(remainderMap.get(remainder), "(");
                result.append(")");
                break;
            }
            
            // Record the current remainder and its position
            remainderMap.put(remainder, result.length());
            
            // Multiply remainder by 10 for the next decimal digit
            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }
        
        return result.toString();
    }
}