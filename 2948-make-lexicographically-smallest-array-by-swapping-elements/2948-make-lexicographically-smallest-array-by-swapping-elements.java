import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        // Sort indices based on their corresponding values in nums
        Arrays.sort(idx, Comparator.comparingInt(i -> nums[i]));

        int[] ans = new int[n];
        for (int i = 0; i < n; ) {
            int j = i + 1;
            // Find all elements that belong to the same connected component
            while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                j++;
            }

            // Extract the sub-array of indices for this group
            Integer[] groupIndices = Arrays.copyOfRange(idx, i, j);
            
            // Sort indices to assign smaller values to earlier positions
            Arrays.sort(groupIndices);

            // Assign the sorted values to the sorted original indices
            for (int k = 0; k < groupIndices.length; k++) {
                ans[groupIndices[k]] = nums[idx[i + k]];
            }

            i = j; // Move to the next group
        }

        return ans;
    }
}