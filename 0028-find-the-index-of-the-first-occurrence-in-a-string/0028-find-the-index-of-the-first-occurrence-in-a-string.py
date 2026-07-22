class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        m = len(needle)
        n = len(haystack)
        
        # If needle is longer than haystack, it can't be inside it
        if m > n:
            return -1
            
        # Iterate through haystack, stopping when the remaining characters 
        # are fewer than the needle's length
        for i in range(n - m + 1):
            if haystack[i:i+m] == needle:
                return i
                
        return -1