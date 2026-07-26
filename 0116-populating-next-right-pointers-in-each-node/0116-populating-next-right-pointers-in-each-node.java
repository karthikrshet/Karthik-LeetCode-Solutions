class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        // Start with the leftmost node of the current level
        Node leftmost = root;
        
        while (leftmost.left != null) {
            Node head = leftmost;
            
            while (head != null) {
                // 1. Connect left child to right child
                head.left.next = head.right;
                
                // 2. Connect right child to the next node's left child, if a next node exists
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                
                // Move to the next node in the current level
                head = head.next;
            }
            
            // Move down to the next level
            leftmost = leftmost.left;
        }
        
        return root;
    }
}