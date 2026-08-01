import java.util.Arrays;

class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        
        for (int i = 0; i < n; i++) {
            int papersWithAtLeastH = n - i;
            if (citations[i] >= papersWithAtLeastH) {
                return papersWithAtLeastH;
            }
        }
        
        return 0;
    }
}