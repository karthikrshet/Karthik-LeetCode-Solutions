class Solution:
    def climbStairs(self, n: int) -> int:
        # Base cases for n = 1 and n = 2
        if n == 1:
            return 1
        if n == 2:
            return 2
        
        # Initialize variables to store the previous two step counts
        two_steps_before = 1
        one_step_before = 2
        
        # Calculate ways for steps 3 up to n
        for _ in range(3, n + 1):
            current_steps = one_step_before + two_steps_before
            
            # Update variables for the next iteration
            two_steps_before = one_step_before
            one_step_before = current_steps
            
        return one_step_before