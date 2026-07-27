
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(0, s, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int start, String s, List<String> currentPath, List<List<String>> result) {
        // Base case: if we've reached the end of the string, add a copy of the path
        if (start == s.length()) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                // Choose: add the palindrome substring to our path
                currentPath.add(s.substring(start, end + 1));
                
                // Explore: recurse for the remaining substring
                backtrack(end + 1, s, currentPath, result);
                
                // Un-choose: backtrack by removing the last element
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }
}