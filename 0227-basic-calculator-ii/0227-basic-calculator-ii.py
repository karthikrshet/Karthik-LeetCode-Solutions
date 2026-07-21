class Solution:
    def calculate(self, s: str) -> int:
        if not s:
            return 0
            
        stack = []
        current_num = 0
        last_operator = '+'
        
        for i, char in enumerate(s):
            # 1. Build the number if the character is a digit
            if char.isdigit():
                current_num = current_num * 10 + int(char)
                
            # 2. Process the number if we hit an operator OR the end of the string
            if char in "+-*/" or i == len(s) - 1:
                if last_operator == '+':
                    stack.append(current_num)
                elif last_operator == '-':
                    stack.append(-current_num)
                elif last_operator == '*':
                    stack.append(stack.pop() * current_num)
                elif last_operator == '/':
                    # Use int(a / b) to truncate toward zero properly in Python
                    stack.append(int(stack.pop() / current_num))
                    
                # 3. Reset for the next number
                last_operator = char
                current_num = 0
                
        # 4. The stack now only contains numbers to be added together
        return sum(stack)