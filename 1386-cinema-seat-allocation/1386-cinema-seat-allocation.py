class Solution:
    def maxNumberOfFamilies(self, n: int, reservedSeats: List[List[int]]) -> int:
        from collections import defaultdict
        
        # Track reserved seats as a set per row to prevent any mask confusion
        row_reservations = defaultdict(set)
        for row, seat in reservedSeats:
            row_reservations[row].add(seat)
            
        # Start by assuming maximum possible groups (2 per row)
        total_groups = 2 * n
        
        # Process rows with reservations
        for row, seats in row_reservations.items():
            # Subtract the assumed 2 groups for this row first
            total_groups -= 2
            
            # Check availability of the 3 possible 4-person groups
            left_free = all(s not in seats for s in [2, 3, 4, 5])
            right_free = all(s not in seats for s in [6, 7, 8, 9])
            middle_free = all(s not in seats for s in [4, 5, 6, 7])
            
            if left_free and right_free:
                total_groups += 2
            elif left_free or right_free or middle_free:
                total_groups += 1
            # If none are free, total_groups stays reduced by 2
                
        return total_groups