class Solution:
    def judgeCircle(self, moves: str) -> bool:
        # The robot returns to the origin if vertical moves cancel out 
        # and horizontal moves cancel out.
        return moves.count('U') == moves.count('D') and moves.count('L') == moves.count('R')