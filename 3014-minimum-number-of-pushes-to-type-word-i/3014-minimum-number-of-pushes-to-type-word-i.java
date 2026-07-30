class Solution {
    public int minimumPushes(String word) {
        int totalPushes = 0;
        int n = word.length();
        
        for (int i = 0; i < n; i++) {
            // Every block of 8 characters increases the required pushes by 1
            totalPushes += (i / 8) + 1;
        }
        
        return totalPushes;
    }
}