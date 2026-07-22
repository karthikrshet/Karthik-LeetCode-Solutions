from typing import Optional

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def reverseBetween(self, head: Optional['ListNode'], left: int, right: int) -> Optional['ListNode']:
        # If the list is empty or there's nothing to reverse
        if not head or left == right:
            return head
            
        # Dummy node to handle edge cases where the head itself is reversed
        dummy = ListNode(0, head)
        prev = dummy
        
        # Step 1: Navigate to the node just BEFORE the 'left' position
        for _ in range(left - 1):
            prev = prev.next
            
        # Step 2: Set up our pointers for the reversal
        curr = prev.next
        
        # Step 3: Reverse the sublist in one pass
        for _ in range(right - left):
            # The node we are about to move to the front of the reversed section
            node_to_move = curr.next
            
            # Detach node_to_move by bridging curr to the node after node_to_move
            curr.next = node_to_move.next
            
            # Insert node_to_move right after 'prev'
            node_to_move.next = prev.next
            prev.next = node_to_move
            
        return dummy.next