class Solution:
    def firstUniqChar(self, s: str) -> int:
        count = {}
        for char in s:
            count[char] = count.get(char, 0) + 1
            
        for i, char in enumerate(s):
            if count[char] == 1:
                return i
                
        return -1