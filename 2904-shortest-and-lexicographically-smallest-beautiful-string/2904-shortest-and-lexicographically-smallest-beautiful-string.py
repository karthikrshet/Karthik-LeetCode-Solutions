class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        n = len(s)
        best = ""
        
        for i in range(n):
            for j in range(i, n):
                sub = s[i:j+1]
                if sub.count('1') == k:
                    # If this is the first valid substring, or shorter than current best,
                    # or same length but lexicographically smaller, update best.
                    if not best or len(sub) < len(best) or (len(sub) == len(best) and sub < best):
                        best = sub
                        
        return best