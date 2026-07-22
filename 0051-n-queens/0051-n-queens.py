from typing import List

class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        col_set = set()
        pos_diag = set()  # (row + col)
        neg_diag = set()  # (row - col)
        
        res = []
        board = [["."] * n for _ in range(n)]
        
        def backtrack(row: int):
            if row == n:
                res.append(["".join(r) for r in board])
                return
                
            for col in range(n):
                if col in (col_set) or (row + col) in pos_diag or (row - col) in neg_diag:
                    continue
                    
                col_set.add(col)
                pos_diag.add(row + col)
                neg_diag.add(row - col)
                board[row][col] = "Q"
                
                backtrack(row + 1)
                
                col_set.remove(col)
                pos_diag.remove(row + col)
                neg_diag.remove(row - col)
                board[row][col] = "."
                
        backtrack(0)
        return res
    