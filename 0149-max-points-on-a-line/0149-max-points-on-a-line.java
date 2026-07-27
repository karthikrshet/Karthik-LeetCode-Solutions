import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        
        int maxPoints = 1;
        
        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int duplicates = 0;
            int localMax = 0;
            
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                if (dx == 0 && dy == 0) {
                    duplicates++;
                    continue;
                }
                
                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;
                
                // Normalize signs to ensure consistent key representation
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    dy = Math.abs(dy);
                }
                
                String slopeKey = dx + "," + dy;
                int count = slopeMap.getOrDefault(slopeKey, 0) + 1;
                slopeMap.put(slopeKey, count);
                localMax = Math.max(localMax, count);
            }
            
            // Total points on the line = points sharing the slope + anchor point + duplicates
            maxPoints = Math.max(maxPoints, localMax + duplicates + 1);
        }
        
        return maxPoints;
    }
    
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}