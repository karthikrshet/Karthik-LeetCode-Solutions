from functools import cache
from collections import Counter

class Solution:
    @cache
    def isScramble(self, s1: str, s2: str) -> bool:
        # Base case: if strings are identical
        if s1 == s2:
            return True
            
        # Pruning check: lengths and character composition must match
        if len(s1) != len(s2) or Counter(s1) != Counter(s2):
            return False
            
        n = len(s1)
        
        # Try every possible split index
        for i in range(1, n):
            # Case 1: Unswapped check
            if (self.isScramble(s1[:i], s2[:i]) and self.isScramble(s1[i:], s2[i:])):
                return True
                
            # Case 2: Swapped check
            if (self.isScramble(s1[:i], s2[-i:]) and self.isScramble(s1[i:], s2[:-i])):
                return True
                
        return False