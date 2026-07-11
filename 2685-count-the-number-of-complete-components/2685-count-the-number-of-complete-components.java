class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Step 1: Build the Adjacency List
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        // Step 2: Traverse all nodes to find isolated components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // stats[0] will hold the number of vertices in this component
                // stats[1] will hold the sum of the degrees of all vertices in this component
                int[] stats = new int[2];
                
                dfs(i, adj, visited, stats);
                
                // Step 3: The Mathematical Trick
                // In a complete graph, every vertex connects to every OTHER vertex.
                // Therefore, the sum of all degrees MUST exactly equal V * (V - 1)
                if (stats[1] == stats[0] * (stats[0] - 1)) {
                    completeComponents++;
                }
            }
        }

        return completeComponents;
    }

    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, int[] stats) {
        visited[node] = true;
        stats[0]++; // We found a vertex
        stats[1] += adj.get(node).size(); // Add its number of edges (its degree)

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, stats);
            }
        }
    }
}