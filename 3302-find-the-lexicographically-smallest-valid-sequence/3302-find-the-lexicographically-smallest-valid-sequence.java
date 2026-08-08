class Solution {
    public int[] validSequence(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        
        // rightMatch[i] stores the maximum index in word2 that can be fully matched 
        // using a suffix of word1 starting at or after index i (without using any mismatch).
        int[] rightMatch = new int[n1 + 1];
        Arrays.fill(rightMatch, n2);
        
        int j = n2 - 1;
        for (int i = n1 - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            rightMatch[i] = j + 1; // first index in word2 that can be matched from i onwards
        }
        
        int[] result = new int[n2];
        int w2Idx = 0;
        boolean usedMismatch = false;
        
        for (int i = 0; i < n1; i++) {
            if (w2Idx >= n2) break;
            
            if (word1.charAt(i) == word2.charAt(w2Idx)) {
                result[w2Idx++] = i;
            } else if (!usedMismatch && rightMatch[i + 1] <= w2Idx + 1) {
                // We can afford to use our single mismatch here because the rest of word2 
                // can be matched from the remaining part of word1.
                result[w2Idx++] = i;
                usedMismatch = true;
            }
        }
        
        if (w2Idx == n2) {
            return result;
        }
        
        return new int[0];
    }
}