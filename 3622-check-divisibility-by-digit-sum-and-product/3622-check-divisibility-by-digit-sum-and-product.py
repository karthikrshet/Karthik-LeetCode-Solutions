class Solution:
    def checkDivisibility(self, n: int) -> bool:
        # Extract digits from n
        digits = [int(char) for char in str(n)]
        
        digit_sum = sum(digits)
        
        # Calculate product
        digit_product = 1
        for d in digits:
            digit_product *= d
            
        # Check divisibility by (sum + product)
        total = digit_sum + digit_product
        
        return n % total == 0