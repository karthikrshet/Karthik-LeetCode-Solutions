class Solution:
    def missingMultiple(self, nums: List[int], k: int) -> int:
        num_set = set(nums)
        multiplier = 1
        
        while True:
            current_multiple = multiplier * k
            if current_multiple not in num_set:
                return current_multiple
            multiplier += 1