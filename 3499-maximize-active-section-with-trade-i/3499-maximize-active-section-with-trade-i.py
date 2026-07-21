class Solution:
    def maxActiveSectionsAfterTrade(self, s: str) -> int:
        if not s:
            return 0
        
        # 1. Explicitly augment the string as per the problem rules.
        # This guarantees the block pattern always starts and ends with '1'.
        t = '1' + s + '1'
        
        # 2. Parse the string into contiguous block lengths
        blocks = []
        current_char = t[0]  # This will always be '1'
        count = 1
        for i in range(1, len(t)):
            if t[i] == current_char:
                count += 1
            else:
                blocks.append(count)
                current_char = t[i]
                count = 1
        blocks.append(count)
        
        # 3. Extract zero-blocks 
        # Because `t` always starts with '1', '0' blocks are strictly at odd indices (1, 3, 5...)
        Z = blocks[1::2]
        
        # Fast return if there are no '0's to trade
        if not Z:
            return s.count('1')
            
        # Get the top zero blocks to efficiently find the max excluding adjacent ones.
        # We only need the top 3 largest to guarantee finding one that isn't `a` or `b`.
        Z_sorted = sorted([(val, idx) for idx, val in enumerate(Z)], reverse=True)
        Z_top = Z_sorted[:3] 
        
        def get_max_excluding(a, b):
            for val, idx in Z_top:
                if idx != a and idx != b:
                    return val
            return 0
            
        max_net_change = 0
        can_trade = False
        
        # 4. Iterate strictly over internal '1' blocks.
        # They sit exactly between the '0' blocks, at even indices (2, 4, 6...).
        for i in range(1, len(Z)):
            can_trade = True
            O_i = blocks[2 * i]  # The length of the internal '1' block
            a = i - 1            # Index of the '0' block to the left
            b = i                # Index of the '0' block to the right
            
            # Scenario A: Flip the newly merged block
            merged_gain = Z[a] + Z[b]
            
            # Scenario B: Flip the largest '0' block elsewhere
            other_gain = get_max_excluding(a, b) - O_i
            
            # Track the best possible outcome
            max_net_change = max(max_net_change, merged_gain, other_gain)
            
        # 5. Apply the trade if possible
        base_ones = s.count('1')
        if not can_trade:
            return base_ones
            
        return base_ones + max_net_change