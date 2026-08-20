class Solution:
    def minimumCost(self, cost: List[int]) -> int:
        # Sort in descending order to prioritize paying for expensive candies
        cost.sort(reverse=True)
        total_cost = 0
        
        for i in range(len(cost)):
            # Skip every 3rd candy (0-indexed: index 2, 5, 8, etc.)
            if i % 3 == 2:
                continue
            total_cost += cost[i]
            
        return total_cost