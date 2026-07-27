import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        
        // Start from the second-to-last row and move upwards to the root
        for (int i = n - 2; i >= 0; i--) {
            List<Integer> currRow = triangle.get(i);
            List<Integer> nextRow = triangle.get(i + 1);
            
            for (int j = 0; j <= i; j++) {
                int minBelow = Math.min(nextRow.get(j), nextRow.get(j + 1));
                currRow.set(j, currRow.get(j) + minBelow);
            }
        }
        
        // The top element now holds the accumulated minimum path sum
        return triangle.get(0).get(0);
    }
}