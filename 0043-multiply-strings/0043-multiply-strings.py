class Solution:
    def multiply(self, num1: str, num2: str) -> str:
        # Edge case for zero
        if num1 == "0" or num2 == "0":
            return "0"
            
        m, n = len(num1), len(num2)
        # The product of two numbers cannot exceed the sum of their lengths
        res = [0] * (m + n)
        
        # Traverse both strings from right to left
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                # Multiply current digits
                # Note: Converting single characters to int is allowed
                mul = int(num1[i]) * int(num2[j])
                
                # Positions in the result array
                p1, p2 = i + j, i + j + 1
                
                # Add multiplication result to the existing value at p2
                total = mul + res[p2]
                
                # Update the current position with the ones place digit
                res[p2] = total % 10
                # Carry over the tens place digit to the next position
                res[p1] += total // 10
                
        # Find the first non-zero index to strip leading zeros
        start_index = 0
        while start_index < len(res) and res[start_index] == 0:
            start_index += 1
            
        # Convert the remaining array elements to a string
        return "".join(map(str, res[start_index:]))