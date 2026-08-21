import math
from typing import List

class Solution:
    def findKthSmallest(self, coins: List[int], k: int) -> int:
        n = len(coins)
        
        # Helper function to count how many valid amounts are <= mx
        def count_amounts(mx: int) -> int:
            total = 0
            # Iterate over all non-empty subsets using bitmask
            for mask in range(1, 1 << n):
                lcm_val = 1
                set_bits = 0
                
                for j in range(n):
                    if (mask >> j) & 1:
                        set_bits += 1
                        lcm_val = math.lcm(lcm_val, coins[j])
                        # Optimization: if LCM exceeds mx, further multiplications will also exceed it
                        if lcm_val > mx:
                            break
                else:
                    # If the loop didn't break, compute inclusion-exclusion term
                    if set_bits % 2 == 1:
                        total += mx // lcm_val
                    else:
                        total -= mx // lcm_val
            return total

        # Binary search boundaries
        left = 1
        right = k * min(coins)
        ans = right

        while left <= right:
            mid = (left + right) // 2
            if count_amounts(mid) >= k:
                ans = mid
                right = mid - 1
            else:
                left = mid + 1

        return ans