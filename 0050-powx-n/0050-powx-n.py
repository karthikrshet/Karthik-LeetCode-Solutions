class Solution:
    def myPow(self, x: float, n: int) -> float:
        # Base case
        if n == 0:
            return 1.0
            
        # Handle negative powers by inverting x and making n positive
        if n < 0:
            x = 1 / x
            n = -n
            
        res = 1.0
        
        while n > 0:
            # If n is odd, multiply the current x into the result
            if n % 2 == 1:
                res *= x
                
            # Square the base and halve the exponent
            x *= x
            n //= 2
            
        return res