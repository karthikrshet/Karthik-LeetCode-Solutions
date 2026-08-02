import java.math.BigInteger;

class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        if (n < 3) return false;

        // Choose the length of the first number (i ranges from 0 to n/2 roughly)
        for (int i = 0; i < n / 2; i++) {
            // First number cannot have leading zeros unless it's just "0"
            if (num.charAt(0) == '0' && i > 0) break;
            
            BigInteger num1 = new BigInteger(num.substring(0, i + 1));

            // Choose the length of the second number
            for (int j = i + 1; n - 1 - j >= Math.max(i + 1, j - i); j++) {
                // Second number cannot have leading zeros unless it's just "0"
                if (num.charAt(i + 1) == '0' && j > i + 1) break;

                BigInteger num2 = new BigInteger(num.substring(i + 1, j + 1));

                if (isValid(num1, num2, j + 1, num)) {
                    return true;
                }
            } 
        }

        return false;
    }

    private boolean isValid(BigInteger n1, BigInteger n2, int index, String num) {
        if (index == num.length()) return true;

        BigInteger sum = n1.add(n2);
        String sumStr = sum.toString();

        // Check if the remaining string starts with the sum string
        if (!num.startsWith(sumStr, index)) {
            return false;
        }

        // Recursively check the next numbers in the sequence
        return isValid(n2, sum, index + sumStr.length(), num);
    }
}