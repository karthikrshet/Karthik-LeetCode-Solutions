from typing import List
import collections

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        # defaultdict automatically initializes missing keys with an empty list
        anagram_map = collections.defaultdict(list)
        
        for s in strs:
            # Create an array of 26 zeros to count character frequencies
            count = [0] * 26
            
            for char in s:
                # Map 'a' -> 0, 'b' -> 1, ..., 'z' -> 25
                count[ord(char) - ord('a')] += 1
                
            # Tuples are immutable and hashable, making them valid dictionary keys
            anagram_map[tuple(count)].append(s)
            
        # Return all the grouped anagram lists
        return list(anagram_map.values())