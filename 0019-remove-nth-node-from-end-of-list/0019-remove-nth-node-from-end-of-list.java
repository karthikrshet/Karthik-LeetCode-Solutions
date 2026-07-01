class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create dummy node to handle edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove nth node
        slow.next = slow.next.next;

        return dummy.next;
    }
}