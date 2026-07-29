class Solution {
    public int titleToNumber(String columnTitle) {
        int result = 0;
        
        for (int i = 0; i < columnTitle.length(); i++) {
            // Convert character to a number from 1 to 26
            int val = columnTitle.charAt(i) - 'A' + 1;
            
            // Shift the current result by one base-26 position and add the new value
            result = result * 26 + val;
        }
        
        return result;
    }
}