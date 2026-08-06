class Solution:
    def smallestNumber(self, n: int, t: int) -> int:
        def digit_product(num):
            prod = 1
            for char in str(num):
                prod *= int(char)
            return prod

        curr = n
        while True:
            if digit_product(curr) % t == 0:
                return curr
            curr += 1