class Solution:
    def countAndSay(self, n: int) -> str:
        # Base case
        if n == 1:
            return "1"
            
        current = "1"
        
        # Build the sequence iteratively n - 1 times
        for _ in range(n - 1):
            next_str = []
            count = 1
            
            # Iterate through the current string to generate the next
            for i in range(1, len(current)):
                if current[i] == current[i - 1]:
                    count += 1
                else:
                    # Group ends, append count and character
                    next_str.append(str(count))
                    next_str.append(current[i - 1])
                    # Reset count for the new character
                    count = 1
                    
            # Don't forget to append the final group
            next_str.append(str(count))
            next_str.append(current[-1])
            
            # Update current for the next generation
            current = "".join(next_str)
            
        return current