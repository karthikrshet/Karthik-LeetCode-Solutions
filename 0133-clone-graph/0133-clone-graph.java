/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // Map to save the visited node and its respective clone
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // If the node was already visited, return the cloned node from the map
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone for the given node
        Node cloneNode = new Node(node.val, new ArrayList<>());
        
        // Add the node to the map BEFORE iterating through neighbors to avoid cycles
        visited.put(node, cloneNode);

        // Iterate through the neighbors to generate their clones
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }
}