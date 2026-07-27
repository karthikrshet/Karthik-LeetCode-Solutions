class Solution {
    public ListNode sortList(ListNode head) {
        // Base case: if list is empty or has only one node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Step 1. Split the list into two halves using slow and fast pointers
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Cut the first half from the second half
        prev.next = null;
        
        // Step 2. Recursively sort both halves
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        
        // Step 3. Merge the sorted halves
        return merge(l1, l2);
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        
        if (l1 != null) {
            tail.next = l1;
        } else if (l2 != null) {
            tail.next = l2;
        }
        
        return dummyHead.next;
    }
}