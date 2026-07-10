import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Pair values with their original indices and sort them
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Map original index -> sorted index for quick O(1) lookups
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[arr[i][1]] = i;
        }
        
        // Step 2: Build the Binary Lifting Table
        // LOG = 18 is enough because 2^17 (131,072) > maximum possible N (10^5)
        int LOG = 18;
        // up[i][k] stores the furthest sorted index we can reach from index 'i' in exactly 2^k steps.
        int[][] up = new int[n][LOG];
        
        // Base case: up[i][0] (furthest we can reach in 1 step)
        int j = 0;
        for (int i = 0; i < n; i++) {
            // Greedily stretch 'j' as far right as maxDiff allows
            while (j + 1 < n && arr[j + 1][0] - arr[i][0] <= maxDiff) {
                j++;
            }
            up[i][0] = j;
        }
        
        // Populate the rest of the table using dynamic programming
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                // To jump 2^k steps, jump 2^(k-1) steps, then jump 2^(k-1) steps again.
                up[i][k] = up[ up[i][k - 1] ][k - 1];
            }
        }
        
        // Step 3: Answer each query in O(log N) time
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            
            if (u == v) {
                ans[i] = 0;
                continue;
            }
            
            int posU = pos[u];
            int posV = pos[v];
            
            // Because the graph is undirected, jumping A -> B is the same distance as B -> A.
            // We always query from the smaller value to the larger value.
            if (posU > posV) {
                int temp = posU;
                posU = posV;
                posV = temp;
            }
            
            // If the maximum possible reach after "infinite" steps is still less than posV, 
            // they are in disconnected components.
            if (up[posU][LOG - 1] < posV) {
                ans[i] = -1;
                continue;
            }
            
            // Binary lift to accumulate the minimum steps
            int curr = posU;
            int steps = 0;
            
            for (int k = LOG - 1; k >= 0; k--) {
                // If taking 2^k steps doesn't overshoot or reach our destination, take those steps!
                if (up[curr][k] < posV) {
                    curr = up[curr][k];
                    steps += (1 << k);
                }
            }
            
            // At this point, 'curr' is exactly 1 step away from reaching/passing posV.
            ans[i] = steps + 1;
        }
        
        return ans;
    }
}