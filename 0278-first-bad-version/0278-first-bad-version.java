/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        
        while (left < right) {
            // Prevent integer overflow
            int mid = left + (right - left) / 2;
            
            if (isBadVersion(mid)) {
                right = mid; // Look on the left side, including mid
            } else {
                left = mid + 1; // Look on the right side
            }
        }
        
        return left;
    }
}