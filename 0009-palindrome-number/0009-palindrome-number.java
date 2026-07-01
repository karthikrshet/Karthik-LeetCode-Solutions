class Solution {
    public boolean isPalindrome(int x) {
        // Negative or ending with 0 (except 0 itself)
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;

        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // For even digits → x == reversedHalf
        // For odd digits → x == reversedHalf / 10
        return x == reversedHalf || x == reversedHalf / 10;
    }
}