class Solution:
    def longestValidParentheses(self, s: str) -> int:
        left = 0
        right = 0
        max_len = 0
        
        # Pass 1: Left to Right
        for char in s:
            if char == '(':
                left += 1
            else:
                right += 1
                
            if left == right:
                max_len = max(max_len, 2 * right)
            elif right > left:
                # Invalid state, reset counters
                left = right = 0
                
        # Reset counters for the second pass
        left = right = 0
        
        # Pass 2: Right to Left
        for char in reversed(s):
            if char == '(':
                left += 1
            else:
                right += 1
                
            if left == right:
                max_len = max(max_len, 2 * left)
            elif left > right:
                # Invalid state for reverse traversal, reset counters
                left = right = 0
                
        return max_len