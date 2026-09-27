class Solution:

  def reverseParentheses(self, s: str) -> str:
    stack = []
    for char in s:
      if char == ')':
        # Extract characters inside the current parentheses
        curr = []
        while stack and stack[-1] != '(':
          curr.append(stack.pop())
        # Pop the '(' as well
        if stack and stack[-1] == '(':
          stack.pop()
        # Push the reversed substring back to the stack
        stack.extend(curr)
      else:
        stack.append(char)
    return ''.join(stack)