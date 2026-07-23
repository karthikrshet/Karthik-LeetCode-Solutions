# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def rotateRight(self, head: [ListNode], k: int) -> [ListNode]:
        if not head or not head.next or k == 0:
            return head
        
        # Find the length and the tail of the list
        tail = head
        length = 1
        while tail.next:
            tail = tail.next
            length += 1
            
        # Optimize k
        k %= length
        if k == 0:
            return head
            
        # Make the list circular
        tail.next = head
        
        # Find the new tail: (length - k - 1) steps from head
        new_tail = head
        for _ in range(length - k - 1):
            new_tail = new_tail.next
            
        new_head = new_tail.next
        new_tail.next = None
        
        return new_head