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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int minDistance = Integer.MAX_VALUE;
        int firstIndex = -1;
        int lastIndex = -1;
        int currentIndex = 1;

        ListNode prev = head;
        if (prev == null || prev.next == null) return new int[]{-1, -1};
        ListNode curr = prev.next;

        while (curr.next != null) {
            ListNode nextNode = curr.next;
            
            // Check for local minima or local maxima
            if ((curr.val > prev.val && curr.val > nextNode.val) || 
                (curr.val < prev.val && curr.val < nextNode.val)) {
                
                if (lastIndex != -1) {
                    minDistance = Math.min(minDistance, currentIndex - lastIndex);
                }
                
                if (firstIndex == -1) {
                    firstIndex = currentIndex;
                }
                
                lastIndex = currentIndex;
            }

            prev = curr;
            curr = nextNode;
            currentIndex++;
        }

        if (minDistance == Integer.MAX_VALUE || firstIndex == lastIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = lastIndex - firstIndex;
        return new int[]{minDistance, maxDistance};
    }
}