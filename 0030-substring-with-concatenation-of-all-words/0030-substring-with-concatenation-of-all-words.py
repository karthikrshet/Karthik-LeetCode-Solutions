from typing import List
from collections import Counter

class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        if not s or not words:
            return []
            
        word_len = len(words[0])
        num_words = len(words)
        total_len = word_len * num_words
        
        # Base case: if the string is shorter than the required concatenation
        if len(s) < total_len:
            return []
            
        word_count = Counter(words)
        result = []
        
        # We need to run the sliding window `word_len` times
        # to cover all possible starting offsets.
        for i in range(word_len):
            left = i
            right = i
            current_count = Counter()
            words_used = 0
            
            # Slide the window by word_len steps
            while right + word_len <= len(s):
                # Extract the current word
                current_word = s[right : right + word_len]
                right += word_len
                
                if current_word in word_count:
                    current_count[current_word] += 1
                    words_used += 1
                    
                    # If we have more occurrences of current_word than needed,
                    # shrink the window from the left until it's valid again.
                    while current_count[current_word] > word_count[current_word]:
                        left_word = s[left : left + word_len]
                        current_count[left_word] -= 1
                        words_used -= 1
                        left += word_len
                        
                    # If we successfully formed a valid concatenation, save the start index
                    if words_used == num_words:
                        result.append(left)
                        
                else:
                    # The word is not part of the target words at all.
                    # Reset the window completely.
                    current_count.clear()
                    words_used = 0
                    left = right
                    
        return result