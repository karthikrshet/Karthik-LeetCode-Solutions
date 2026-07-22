class Solution:
    def totalNQueens(self, n: int) -> int:
        col_set = set()
        pos_diag = set()  # (row + col)
        neg_diag = set()  # (row - col)
        
        count = 0
        
        def backtrack(row: int):
            nonlocal count
            if row == n:
                count += 1
                return
                
            for col in range(n):
                if col in col_set or (row + col) in pos_diag or (row - col) in neg_diag:
                    continue
                    
                col_set.add(col)
                pos_diag.add(row + col)
                neg_diag.add(row - col)
                
                backtrack(row + 1)
                
                col_set.remove(col)
                pos_diag.remove(row + col)
                neg_diag.remove(row - col)
                
        backtrack(0)
        return count