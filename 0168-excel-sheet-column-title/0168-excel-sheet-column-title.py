class Solution:
    def convertToTitle(self, columnNumber: int) -> str:
        result = []
        
        while columnNumber > 0:
            # Shift to 0-indexed to easily map to 0-25
            columnNumber -= 1
            
            # Find the character for the current position
            remainder = columnNumber % 26
            result.append(chr(remainder + ord('A')))
            
            # Move to the next "base-26 digit"
            columnNumber //= 26
            
        # The characters were appended from right to left, so reverse the result
        return "".join(reversed(result))