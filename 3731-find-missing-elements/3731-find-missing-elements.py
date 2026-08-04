class Solution:
    def findMissingElements(self, nums: List[int]) -> List[int]:
        min_val = min(nums)
        max_val = max(nums)
        num_set = set(nums)
        
        missing = []
        for i in range(min_val, max_val + 1):
            if i not in num_set:
                missing.append(i)
                
        return missing