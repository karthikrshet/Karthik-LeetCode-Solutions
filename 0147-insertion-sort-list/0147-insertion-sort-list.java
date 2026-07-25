/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode lastSorted = head; // Last node of the sorted sublist
        ListNode curr = head.next;   // Current node to be inserted
        
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                // Already sorted relative to each other, just advance
                lastSorted = lastSorted; // no change
                lastSorted = curr;
                curr = curr.next;
            } else {
                // Need to scan from the beginning to find the insertion point
                ListNode prev = dummy;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                
                // Insert curr between prev and prev.next
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
                
                // Advance curr to the next node to process
                curr = lastSorted.next;
            }
        }
        
        return dummy.next;
    }
}