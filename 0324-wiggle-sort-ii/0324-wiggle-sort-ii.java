import java.util.Arrays;

class Solution {
    public void wiggleSort(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        
        int n = nums.length;
        int mid = (n + 1) / 2; // End of the first half
        
        int j = mid - 1; // Pointer for the smaller half
        int k = n - 1;   // Pointer for the larger half
        
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = sorted[j--];
            } else {
                nums[i] = sorted[k--];
            }
        }
    }
}