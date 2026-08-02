class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        int ans = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canEatInTime(piles, h, mid)) {
                ans = mid;
                right = mid - 1; // Try to find a smaller valid speed
            } else {
                left = mid + 1;  // Too slow, need a higher speed
            }
        }
        
        return ans;
    }
    
    private boolean canEatInTime(int[] piles, int h, int k) {
        long hours = 0;
        for (int pile : piles) {
            // Equivalent to ceil((double) pile / k)
            hours += (pile + k - 1) / k;
            if (hours > h) {
                return false; // Optimization: early exit if hours exceed h
            }
        }
        return hours <= h;
    }
}