from typing import List

class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        result = []
        
        def backtrack(start: int, current_combo: List[int]):
            # 1. Base Case: The combination has reached the target size of k
            if len(current_combo) == k:
                # Append a copy of the current list to the result
                result.append(list(current_combo))
                return
            
            # 2. Pruning Optimization
            # Calculate how many more numbers we need to complete this combination
            needed = k - len(current_combo)
            
            # We only iterate up to a point where there are enough numbers left.
            # Example: n=4, needed=2. We can only start at 1, 2, or 3. Starting at 4 leaves no room.
            for i in range(start, n - needed + 2):
                # Choose the number
                current_combo.append(i)
                
                # Explore further, incrementing 'start' to avoid duplicates and permutations
                backtrack(i + 1, current_combo)
                
                # Backtrack: Undo the choice to explore the next number in the loop
                current_combo.pop()

        # Initiate the recursive backtracking starting at number 1
        backtrack(1, [])
        return result