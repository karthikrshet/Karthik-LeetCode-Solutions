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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private ListNode currentHead;

    public TreeNode sortedListToBST(ListNode head) {
        int length = getLength(head);
        currentHead = head;
        return buildBST(0, length - 1);
    }
    
    private int getLength(ListNode head) {
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        return len;
    }
    
    private TreeNode buildBST(int left, int right) {
        if (left > right) {
            return null;
        }
        
        int mid = left + (right - left) / 2;
        
        // Recursively build the left subtree
        TreeNode leftChild = buildBST(left, mid - 1);
        
        // Construct the root using the current linked list node
        TreeNode root = new TreeNode(currentHead.val);
        root.left = leftChild;
        
        // Advance the linked list pointer
        currentHead = currentHead.next;
        
        // Recursively build the right subtree
        root.right = buildBST(mid + 1, right);
        
        return root;
    }
}