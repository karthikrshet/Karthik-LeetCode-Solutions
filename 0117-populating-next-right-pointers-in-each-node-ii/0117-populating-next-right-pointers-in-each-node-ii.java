class Solution {
    public Node connect(Node root) {
        Node curr = root; // Current node on the upper level
        
        while (curr != null) {
            Node dummyHead = new Node(0); // Dummy head for the next level
            Node tail = dummyHead; // Tail pointer to build the next level list
            
            // Traverse the current level using 'next' pointers
            while (curr != null) {
                if (curr.left != null) {
                    tail.next = curr.left;
                    tail = tail.next;
                }
                if (curr.right != null) {
                    tail.next = curr.right;
                    tail = tail.next;
                }
                curr = curr.next;
            }
            
            // Move down to the start of the next level
            curr = dummyHead.next;
        }
        
        return root;
    }
}