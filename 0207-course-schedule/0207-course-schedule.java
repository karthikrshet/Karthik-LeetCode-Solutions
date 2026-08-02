
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        int[] inDegree = new int[numCourses];
        
        // Build graph and populate in-degrees
        for (int[] p : prerequisites) {
            int course = p[0];
            int prereq = p[1];
            adj.get(prereq).add(course);
            inDegree[course]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int processedCourses = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            processedCourses++;
            
            for (int neighbor : adj.get(curr)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return processedCourses == numCourses;
    }
}