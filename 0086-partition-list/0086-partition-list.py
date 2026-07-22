# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def partition(self, head: Optional[ListNode], x: int) -> Optional[ListNode]:
        # Create dummy heads for both partitions
        less_dummy = ListNode(0)
        greater_dummy = ListNode(0)
        
        # Pointers to track the tails of the two lists
        less = less_dummy
        greater = greater_dummy
        
        current = head
        
        while current:
            if current.val < x:
                less.next = current
                less = less.next
            else:
                greater.next = current
                greater = greater.next
            current = current.next
            
        # Terminate the greater list to avoid cycles
        greater.next = None
        
        # Connect the two lists together
        less.next = greater_dummy.next
        
        return less_dummy.next