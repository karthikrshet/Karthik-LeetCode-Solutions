class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Assign a "Component ID" to every node
        int[] component = new int[n];
        int currentComponent = 0;
        
        // component[0] defaults to 0
        for (int i = 1; i < n; i++) {
            // If the gap between adjacent numbers is strictly greater than maxDiff, 
            // the graph is completely severed here. We start a new component.
            if (nums[i] - nums[i - 1] > maxDiff) {
                currentComponent++;
            }
            // Assign the current node to the active component
            component[i] = currentComponent;
        }
        
        // Step 2: Answer each query in O(1) time
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int nodeU = queries[i][0];
            int nodeV = queries[i][1];
            
            // If they share the same Component ID, a path exists!
            ans[i] = (component[nodeU] == component[nodeV]);
        }
        
        return ans;
    }
}