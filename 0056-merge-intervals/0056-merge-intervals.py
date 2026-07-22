from typing import List

class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        if not intervals:
            return []
            
        # Sort intervals based on the starting value
        intervals.sort(key=lambda x: x[0])
        
        merged = [intervals[0]]
        
        for current in intervals[1:]:
            last_merged = merged[-1]
            
            # If current interval overlaps with the last merged one, merge them
            if current[0] <= last_merged[1]:
                last_merged[1] = max(last_merged[1], current[1])
            else:
                # No overlap, add the current interval to the result
                merged.append(current)
                
        return merged