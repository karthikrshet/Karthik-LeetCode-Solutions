# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        # 1. Count how many nodes we have in total
        count = 0
        curr = head
        while curr:
            count += 1
            curr = curr.next
            
        # 2. Set up a dummy node to anchor the start of our list
        dummy = ListNode(0)
        dummy.next = head
        
        # group_prev will always point to the node immediately BEFORE the group we are reversing
        group_prev = dummy
        
        # 3. Process groups only if we have at least k nodes left
        while count >= k:
            # curr is the first node of the group (it will become the last node after reversal)
            curr = group_prev.next
            prev = None
            
            # Standard linked list reversal for exactly k nodes
            for _ in range(k):
                temp = curr.next
                curr.next = prev
                prev = curr
                curr = temp
            
            # After the loop:
            # 'prev' is the new head of this reversed group.
            # 'curr' is the first node of the NEXT group.
            
            # The original first node of the group (group_prev.next) is now the tail.
            # Connect it to the start of the next group (curr).
            tail = group_prev.next
            tail.next = curr
            
            # Connect the node before this group to the new head of this group
            group_prev.next = prev
            
            # Move group_prev forward to the end of this newly reversed group to prep for the next loop
            group_prev = tail
            
            # We successfully processed a group, deduct k from our total count
            count -= k
            
        return dummy.next