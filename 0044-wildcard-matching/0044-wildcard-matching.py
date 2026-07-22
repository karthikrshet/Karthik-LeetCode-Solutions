class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        s_ptr = 0
        p_ptr = 0
        star_idx = -1
        match_idx = -1
        
        while s_ptr < len(s):
            # Case 1: Characters match or pattern is '?'
            if p_ptr < len(p) and (p[p_ptr] == s[s_ptr] or p[p_ptr] == '?'):
                s_ptr += 1
                p_ptr += 1
                
            # Case 2: Pattern is '*', record positions and assume empty match
            elif p_ptr < len(p) and p[p_ptr] == '*':
                star_idx = p_ptr
                match_idx = s_ptr
                p_ptr += 1
                
            # Case 3: Mismatch, but a '*' was encountered previously.
            # Backtrack the pattern to the character after '*' and 
            # consume one more character from the string.
            elif star_idx != -1:
                p_ptr = star_idx + 1
                match_idx += 1
                s_ptr = match_idx
                
            # Case 4: Mismatch and no '*' to fall back on
            else:
                return False
                
        # Check for remaining characters in pattern; they must all be '*'
        while p_ptr < len(p) and p[p_ptr] == '*':
            p_ptr += 1
            
        return p_ptr == len(p)