class Solution:
    def countCommas(self, n: int) -> int:
        total_commas = 0
        p = 1000  # First comma boundary
        
        while p <= n:
            # All numbers from p to n contribute at least one comma at this position
            total_commas += (n - p + 1)
            
            # Move to the next comma boundary (e.g., 1,000 -> 1,000,000 -> 1,000,000,000)
            p *= 1000
            
        return total_commas