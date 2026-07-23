class Solution:
    def addBinary(self, a: str, b: str) -> str:
        result = []
        i, j = len(a) - 1, len(b) - 1
        carry = 0
        
        while i >= 0 or j >= 0 or carry:
            total = carry
            
            if i >= 0:
                total += int(a[i])
                i -= 1
                
            if j >= 0:
                total += int(b[j])
                j -= 1
                
            carry = total // 2
            result.append(str(total % 2))
            
        # Since we appended from right to left, reverse the result list and join
        return "".join(reversed(result))