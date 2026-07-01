import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        if (digits == null || digits.length() == 0) return result;
        
        String[] mapping = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };
        
        backtrack(result, digits, mapping, 0, new StringBuilder());
        return result;
    }
    
    private void backtrack(List<String> result, String digits, String[] mapping, int index, StringBuilder current) {
        
        // Base case
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        String letters = mapping[digits.charAt(index) - '0'];
        
        for (char c : letters.toCharArray()) {
            current.append(c);              // choose
            backtrack(result, digits, mapping, index + 1, current); // explore
            current.deleteCharAt(current.length() - 1); // un-choose
        }
    }
}