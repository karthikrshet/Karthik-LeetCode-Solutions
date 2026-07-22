from typing import List

class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        res = []
        i = 0
        n = len(intervals)
        
        # Phase 1: Add all intervals that end before the new interval starts
        while i < n and intervals[i][1] < newInterval[0]:
            res.append(intervals[i])
            i += 1
            
        # Phase 2: Merge all overlapping intervals with the new interval
        while i < n and intervals[i][0] <= newInterval[1]:
            newInterval[0] = min(newInterval[0], intervals[i][0])
            newInterval[1] = max(newInterval[1], intervals[i][1])
            i += 1
            
        res.append(newInterval)
        
        # Phase 3: Add all remaining intervals
        while i < n:
            res.append(intervals[i])
            i += 1
            
        return res