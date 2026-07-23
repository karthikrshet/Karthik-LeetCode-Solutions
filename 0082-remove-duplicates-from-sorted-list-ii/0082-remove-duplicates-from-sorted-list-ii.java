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
    public ListNode deleteDuplicates(ListNode head) {
        // Dummy node to handle edge cases where the head itself has duplicates
        ListNode dummy = new ListNode(0, head);
        ListNode pred = dummy;
        
        while (head != null) {
            // If it's a beginning of duplicates sub-list, skip all duplicates
            if (head.next != null && head.val == head.next.val) {
                // Move till the end of duplicates sub-list
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // Skip all duplicates
                pred.next = head.next;
            } else {
                // Otherwise, no duplicates detected, move predecessor pointer
                pred = pred.next;
            }
            
            // Move forward
            head = head.next;
        }
        
        return dummy.next;
    }
}