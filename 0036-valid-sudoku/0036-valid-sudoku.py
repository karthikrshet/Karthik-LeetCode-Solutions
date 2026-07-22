import collections
from typing import List

class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        # Use dictionaries of sets to keep track of seen numbers
        rows = collections.defaultdict(set)
        cols = collections.defaultdict(set)
        boxes = collections.defaultdict(set)  # Key will be a tuple: (r // 3, c // 3)
        
        for r in range(9):
            for c in range(9):
                val = board[r][c]
                
                # Skip empty cells
                if val == '.':
                    continue
                
                # Check for duplicates in the current row, col, or box
                if (val in rows[r] or 
                    val in cols[c] or 
                    val in boxes[(r // 3, c // 3)]):
                    return False
                
                # Add the value to our sets
                rows[r].add(val)
                cols[c].add(val)
                boxes[(r // 3, c // 3)].add(val)
                
        return True