
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }
    
    private void backtrack(List<String> result, StringBuilder current, int openCount, int closeCount, int max) {
        // Base case: If the string is fully formed, add it to the results
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }
        
        // Rule 1: Add an open parenthesis if we haven't reached the max 'n'
        if (openCount < max) {
            current.append("(");
            backtrack(result, current, openCount + 1, closeCount, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
        
        // Rule 2: Add a close parenthesis if there are unmatched open parentheses
        if (closeCount < openCount) {
            current.append(")");
            backtrack(result, current, openCount, closeCount + 1, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}