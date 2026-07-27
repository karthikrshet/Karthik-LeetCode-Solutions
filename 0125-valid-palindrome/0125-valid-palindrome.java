class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            char cLeft = s.charAt(left);
            char cRight = s.charAt(right);
            
            // Skip non-alphanumeric characters from the left
            if (!Character.isLetterOrDigit(cLeft)) {
                left++;
            } 
            // Skip non-alphanumeric characters from the right
            else if (!Character.isLetterOrDigit(cRight)) {
                right--;
            } 
            // Compare characters case-insensitively
            else {
                if (Character.toLowerCase(cLeft) != Character.toLowerCase(cRight)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        
        return true;
    }
}