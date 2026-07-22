from typing import List
import bisect

class SegmentTree:
    def __init__(self, data):
        if not data:
            self.n = 0
            self.tree = []
            return
        self.n = len(data)
        self.tree = [0] * (2 * self.n)
        
        # Insert leaf nodes in tree
        for i in range(self.n):
            self.tree[self.n + i] = data[i]
            
        # Build the tree by calculating parents
        for i in range(self.n - 1, 0, -1):
            self.tree[i] = max(self.tree[2 * i], self.tree[2 * i + 1])
            
    def query(self, l, r):
        # Queries the maximum in the range [l, r] inclusive
        if l > r or self.n == 0:
            return 0
        res = 0
        l += self.n
        r += self.n + 1
        while l < r:
            if l % 2 == 1:
                res = max(res, self.tree[l])
                l += 1
            if r % 2 == 1:
                r -= 1
                res = max(res, self.tree[r])
            l //= 2
            r //= 2
        return res

class Solution:
    def maxActiveSectionsAfterTrade(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        total_ones = s.count('1')
        
        # 1. Extract all contiguous '0' blocks: (start_index, end_index, length)
        Z = []
        i = 0
        while i < n:
            if s[i] == '0':
                start = i
                while i < n and s[i] == '0':
                    i += 1
                Z.append((start, i - 1, i - start))
            else:
                i += 1
                
        starts = [z[0] for z in Z]
        ends = [z[1] for z in Z]
        
        # 2. Precompute the sum of all adjacent '0' blocks
        adjacent_sums = []
        for j in range(len(Z) - 1):
            adjacent_sums.append(Z[j][2] + Z[j+1][2])
            
        # 3. Build a Segment tree for O(log N) Range Max Queries
        st = SegmentTree(adjacent_sums)
        ans = []
        
        # 4. Resolve queries
        for l, r in queries:
            # Find which '0' blocks overlap with the query range [l, r]
            a = bisect.bisect_left(ends, l)
            b = bisect.bisect_right(starts, r) - 1
            
            # If 1 or 0 blocks overlap, no valid trade can be made
            if a >= b:
                ans.append(total_ones)
            else:
                # Truncate the boundary blocks based on l and r
                za_len = min(r, Z[a][1]) - max(l, Z[a][0]) + 1
                zb_len = min(r, Z[b][1]) - max(l, Z[b][0]) + 1
                
                # If only exactly 2 blocks overlap
                if b == a + 1:
                    gain = za_len + zb_len
                # If 3 or more blocks overlap, check the boundaries and the fully enclosed blocks
                else:
                    gain = max(za_len + Z[a+1][2], Z[b-1][2] + zb_len)
                    
                    # Query segment tree for blocks completely inside the substring
                    if b - 2 >= a + 1:
                        gain = max(gain, st.query(a + 1, b - 2))
                        
                ans.append(total_ones + gain)
                
        return ans