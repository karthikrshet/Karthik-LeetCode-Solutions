import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Step 1: Sort the intervals
        // Sort by start time (ascending). 
        // If start times are the same, sort by end time (descending).
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int remainingCount = 0;
        int maxEndSeenSoFar = -1;

        // Step 2: Iterate and count
        for (int[] interval : intervals) {
            int currentEnd = interval[1];

            // If this interval reaches further than any we've seen, 
            // it is NOT covered. We count it and update our max boundary.
            if (currentEnd > maxEndSeenSoFar) {
                remainingCount++;
                maxEndSeenSoFar = currentEnd;
            }
           
            // This means it is completely covered! We just ignore it.
        }

        return remainingCount;
    }
}