import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count character frequencies
        int[] freq = new int[26];
        for (int i = 0; i < word.length(); i++) {
            freq[word.charAt(i) - 'a']++;
        }
        
        // Step 2: Sort frequencies in ascending order
        Arrays.sort(freq);
        
        int totalPushes = 0;
        
        // Step 3: Traverse from highest frequency to lowest
        for (int i = 0; i < 26; i++) {
            int currentFreq = freq[25 - i];
            
            // If frequency is 0, we've processed all existing characters
            if (currentFreq == 0) {
                break;
            }
            
            // Calculate the cost based on the group of 8
            int cost = (i / 8) + 1;
            totalPushes += currentFreq * cost;
        }
        
        return totalPushes;
    }
}