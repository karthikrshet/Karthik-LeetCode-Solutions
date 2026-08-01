class Solution {
    public int minOperations(String s) {
        int mismatches = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char expectedChar = (i % 2 == 0) ? '0' : '1';
            if (s.charAt(i) != expectedChar) {
                mismatches++;
            }
        }
        
        // Minimum between matching pattern starting with '0' and pattern starting with '1'
        return Math.min(mismatches, s.length() - mismatches);
    }
}