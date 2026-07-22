class Solution:
    def divide(self, dividend: int, divisor: int) -> int:
        MAX_INT = 2147483647  # 2**31 - 1
        MIN_INT = -2147483648 # -2**31
        
        # 1. Edge case: Handle 32-bit overflow
        if dividend == MIN_INT and divisor == -1:
            return MAX_INT
            
        # 2. Determine the sign using XOR
        # If one is negative and the other is positive, this evaluates to True
        is_negative = (dividend < 0) ^ (divisor < 0)
        
        # 3. Use absolute values for calculation
        dvd = abs(dividend)
        dvs = abs(divisor)
        
        quotient = 0
        
        # 4. Bitwise division
        while dvd >= dvs:
            temp = dvs
            multiple = 1
            
            # Double the divisor until it's larger than the remaining dividend
            while dvd >= (temp << 1):
                temp <<= 1
                multiple <<= 1
                
            # Subtract the chunk and add to the quotient
            dvd -= temp
            quotient += multiple
            
        # 5. Apply the calculated sign
        if is_negative:
            quotient = -quotient
            
        return quotient