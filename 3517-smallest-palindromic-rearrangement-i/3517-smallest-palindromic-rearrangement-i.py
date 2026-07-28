class Solution:
    def smallestPalindrome(self, s: str) -> str:
        # Step 1: Count character frequencies
        count = [0] * 26
        for char in s:
            count[ord(char) - ord('a')] += 1
            
        first_half = []
        mid_char = ""
        
        # Step 2: Build the first half lexicographically
        for i in range(26):
            if count[i] > 0:
                char = chr(ord('a') + i)
                # Take half of the occurrences for the left side
                first_half.append(char * (count[i] // 2))
                # If count is odd, it must be the middle element
                if count[i] % 2 != 0:
                    mid_char = char
                    
        left_str = "".join(first_half)
        right_str = left_str[::-1]
        
        # Step 3: Combine parts to form the final palindrome
        return left_str + mid_char + right_str