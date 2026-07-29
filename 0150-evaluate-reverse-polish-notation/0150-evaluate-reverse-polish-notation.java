class Solution {
    public int evalRPN(String[] tokens) {
        // ArrayDeque is faster than java.util.Stack
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    // The first popped element is the right operand
                    int rightSub = stack.pop();
                    int leftSub = stack.pop();
                    stack.push(leftSub - rightSub);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    // The first popped element is the divisor (right operand)
                    int divisor = stack.pop();
                    int dividend = stack.pop();
                    stack.push(dividend / divisor);
                    break;
                default:
                    // If it's not an operator, it must be a number
                    stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
}