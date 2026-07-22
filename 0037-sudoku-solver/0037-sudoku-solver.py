from typing import List

class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        rows = [set() for _ in range(9)]
        cols = [set() for _ in range(9)]
        boxes = [set() for _ in range(9)]
        
        empty_cells = []
        
        # 1. Initialize sets and find all empty cells
        for r in range(9):
            for c in range(9):
                if board[r][c] == '.':
                    empty_cells.append((r, c))
                else:
                    val = board[r][c]
                    box_idx = (r // 3) * 3 + (c // 3)
                    rows[r].add(val)
                    cols[c].add(val)
                    boxes[box_idx].add(val)
                    
        # 2. Backtracking function
        def backtrack(index: int) -> bool:
            # Base Case: All empty cells have been successfully filled
            if index == len(empty_cells):
                return True
            
            r, c = empty_cells[index]
            box_idx = (r // 3) * 3 + (c // 3)
            
            # Try placing digits 1 through 9
            for val in map(str, range(1, 10)):
                if val not in rows[r] and val not in cols[c] and val not in boxes[box_idx]:
                    # Place the digit
                    board[r][c] = val
                    rows[r].add(val)
                    cols[c].add(val)
                    boxes[box_idx].add(val)
                    
                    # Move to the next empty cell
                    if backtrack(index + 1):
                        return True
                        
                    # Backtrack (Undo the placement)
                    board[r][c] = '.'
                    rows[r].remove(val)
                    cols[c].remove(val)
                    boxes[box_idx].remove(val)
                    
            # Trigger backtracking if no valid number can be placed
            return False
            
        # Start the backtracking process from the first empty cell
        backtrack(0)