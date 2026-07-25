

class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return result;
        }
        backtrack(result, "", num, target, 0, 0, 0);
        return result;
    }
    
    private void backtrack(List<String> result, String path, String num, int target, int index, long eval, long multed) {
        // Base case: if we have reached the end of the string, check if the expression equals target
        if (index == num.length()) {
            if (eval == target) {
                result.add(path);
            }
            return;
        }
        
        for (int i = index; i < num.length(); i++) {
            // Numbers cannot have leading zeros (e.g., "05" is invalid, but "0" is valid)
            if (i > index && num.charAt(index) == '0') {
                break;
            }
            
            String subStr = num.substring(index, i + 1);
            long currentVal = Long.parseLong(subStr);
            
            if (index == 0) {
                // First number in the expression doesn't take a preceding operator
                backtrack(result, path + subStr, num, target, i + 1, currentVal, currentVal);
            } else {
                // Try Addition
                backtrack(result, path + "+" + subStr, num, target, i + 1, eval + currentVal, currentVal);
                
                // Try Subtraction
                backtrack(result, path + "-" + subStr, num, target, i + 1, eval - currentVal, -currentVal);
                
                // Try Multiplication (adjusting for precedence)
                backtrack(result, path + "*" + subStr, num, target, i + 1, eval - multed + (multed * currentVal), multed * currentVal);
            }
        }
    }
}