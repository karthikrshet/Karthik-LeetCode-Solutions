/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // Step 1: Clone nodes and interleave them with the original list
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Step 2: Assign random pointers for the copied nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                // The copied node's random is the original's random's next (the cloned random)
                curr.next.random = curr.random.next;
            }
            // Move to the next original node
            curr = curr.next.next;
        }

        // Step 3: Unweave the lists (separate original and copy)
        curr = head;
        Node dummy = new Node(0);
        Node copyTail = dummy;

        while (curr != null) {
            // Extract the copied node
            Node copy = curr.next;
            copyTail.next = copy;
            copyTail = copy;
            
            // Restore the original list's next pointer
            curr.next = copy.next;
            
            // Move to the next original node
            curr = curr.next;
        }

        return dummy.next;
    }
}