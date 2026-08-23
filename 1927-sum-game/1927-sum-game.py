class Solution:
    def sumGame(self, num: str) -> bool:
        n = len(num)
        half = n // 2
        
        sum1, sum2 = 0, 0
        cnt1, cnt2 = 0, 0
        
        # Analyze left half
        for i in range(half):
            if num[i] == '?':
                cnt1 += 1
            else:
                sum1 += int(num[i])
                
        # Analyze right half
        for i in range(half, n):
            if num[i] == '?':
                cnt2 += 1
            else:
                sum2 += int(num[i])
                
        # If total question marks are odd, Alice gets the last move and wins
        if (cnt1 + cnt2) % 2 != 0:
            return True
            
        # Bob can only win if the current sum difference matches the expected '?' compensation
        # Equation: sum1 - sum2 == 9 * (cnt2 - cnt1) / 2
        return (sum1 - sum2) != 9 * (cnt2 - cnt1) // 2