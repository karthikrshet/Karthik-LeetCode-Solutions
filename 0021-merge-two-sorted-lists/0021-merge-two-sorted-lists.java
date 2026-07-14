/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create a dummy node to act as the starting point of our merged list
        ListNode dummy = new ListNode(-1);
        // 'current' will point to the last node in our newly merged list
        ListNode current = dummy;

        // Traverse both lists as long as neither is empty
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;   // Attach the smaller node
                list1 = list1.next;     // Move list1 pointer forward
            } else {
                current.next = list2;   // Attach the smaller node
                list2 = list2.next;     // Move list2 pointer forward
            }
            current = current.next;     // Move the current pointer forward
        }

        // If one of the lists is exhausted, attach the remainder of the other list
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        // The merged list starts at the node right after our dummy node
        return dummy.next;
    }
}