from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def oddEvenList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        # Edge case: if the list is empty or has only one/two nodes
        if not head or not head.next:
            return head
        
        odd = head
        even = head.next
        even_head = even  # Save the start of the even list to attach later
        
        # Traverse and rearrange pointers
        while even and even.next:
            odd.next = even.next
            odd = odd.next
            even.next = odd.next
            even = even.next
            
        # Connect the end of the odd list to the start of the even list
        odd.next = even_head
        
        return head