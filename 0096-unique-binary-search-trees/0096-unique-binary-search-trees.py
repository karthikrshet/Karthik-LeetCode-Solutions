class Solution:
    def numTrees(self, n: int) -> int:
        # dp[k] will store the number of unique BSTs for k nodes
        dp = [0] * (n + 1)
        
        # Base cases
        dp[0] = 1
        dp[1] = 1
        
        # Build the table from 2 up to n
        for nodes in range(2, n + 1):
            for root in range(1, nodes + 1):
                # Number of left subtrees * Number of right subtrees
                left_nodes = root - 1
                right_nodes = nodes - root
                dp[nodes] += dp[left_nodes] * dp[right_nodes]
                
        return dp[n]