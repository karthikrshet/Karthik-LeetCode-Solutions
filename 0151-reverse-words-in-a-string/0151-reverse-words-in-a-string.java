class Solution {
    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {
            // Skip any spaces
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            
            // If we've exhausted the string, break
            if (i < 0) {
                break;
            }
            
            // Mark the end of the word
            int j = i;
            
            // Move pointer i to the space before the word
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            
            // Append a space if this isn't the first word we are adding
            if (result.length() > 0) {
                result.append(" ");
            }
            
            // Append the word itself
            result.append(s.substring(i + 1, j + 1));
        }
        
        return result.toString();
    }
}