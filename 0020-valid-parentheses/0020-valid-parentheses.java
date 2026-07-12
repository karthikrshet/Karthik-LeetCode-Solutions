import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            // When we see an opening bracket, we push its EXACT matching closing bracket onto the stack
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } 
            // If it's a closing bracket, it MUST match the top of our stack
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        
        // If the stack is empty at the end, all brackets were perfectly matched!
        return stack.isEmpty();
    }
}