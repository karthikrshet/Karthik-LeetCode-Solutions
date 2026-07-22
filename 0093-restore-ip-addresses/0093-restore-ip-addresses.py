from typing import List

class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        # Quick exit for impossible string lengths
        if len(s) < 4 or len(s) > 12:
            return []
            
        res = []
        
        def backtrack(i: int, path: List[str]):
            # Base Case 1: Valid IP found
            if len(path) == 4 and i == len(s):
                res.append(".".join(path))
                return
                
            # Base Case 2: Invalid path (too many segments or reached end too early)
            if len(path) == 4 or i == len(s):
                return
                
            # A segment can be 1, 2, or 3 digits long
            for j in range(1, 4):
                # Ensure we don't slice out of bounds
                if i + j > len(s):
                    break
                    
                segment = s[i:i+j]
                
                # Check 1: No leading zeros (unless the segment is exactly "0")
                if len(segment) > 1 and segment[0] == '0':
                    continue
                    
                # Check 2: Value must be between 0 and 255
                if int(segment) > 255:
                    continue
                    
                # Valid segment: Choose, Explore, Un-choose
                path.append(segment)
                backtrack(i + j, path)
                path.pop()
                
        backtrack(0, [])
        return res