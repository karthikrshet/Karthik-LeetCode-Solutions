class Solution {
    public int minScore(int n, int[][] roads) {
        // Step 1: Build an adjacency list for the graph
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int distance = road[2];
            // Since roads are bidirectional, add to both cities
            graph.get(u).add(new int[]{v, distance});
            graph.get(v).add(new int[]{u, distance});
        }
        
        // Step 2: Traverse the graph to find the minimum edge
        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        // Start from City 1
        queue.offer(1);
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int currentCity = queue.poll();
            
            // Look at all connected roads
            for (int[] edge : graph.get(currentCity)) {
                int nextCity = edge[0];
                int roadDistance = edge[1];
                
                // We check the minimum score for EVERY road we see, 
                // even if we've already visited the city on the other side!
                minScore = Math.min(minScore, roadDistance);
                
                // Only queue up cities we haven't explored yet
                if (!visited[nextCity]) {
                    visited[nextCity] = true;
                    queue.offer(nextCity);
                }
            }
        }
        
        return minScore;
    }
}