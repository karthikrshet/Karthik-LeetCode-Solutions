class Solution {
    public String smallestSubsequence(String s) {
        // Step 1: Record the last occurrence index of each character
        int[] lastOccurrence = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }
        
        // Track which characters are currently in our string
        boolean[] seen = new boolean[26];
        StringBuilder stack = new StringBuilder();
        
        // Step 2 & 3: Iterate and build the optimal sequence
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // If we already have the character in our optimal string, skip it
            if (seen[c - 'a']) {
                continue;
            }
            
            // Remove the last character if it's strictly greater than the current one
            // AND it appears again later in the string
            while (stack.length() > 0 && 
                   c < stack.charAt(stack.length() - 1) && 
                   lastOccurrence[stack.charAt(stack.length() - 1) - 'a'] > i) {
                
                // Mark the removed character as not seen
                seen[stack.charAt(stack.length() - 1) - 'a'] = false;
                stack.deleteCharAt(stack.length() - 1);
            }
            
            // Add the current character and mark as seen
            stack.append(c);
            seen[c - 'a'] = true;
        }
        
        // Step 4: Return the built string
        return stack.toString();
    }
}