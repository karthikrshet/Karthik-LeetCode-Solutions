import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) {
            return result;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(s);
        visited.add(s);
        boolean found = false;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String curr = queue.poll();

                if (isValid(curr)) {
                    result.add(curr);
                    found = true;
                }

                if (found) {
                    continue; // Skip generating children if we found valid strings at this minimum depth
                }

                // Generate all possible states by removing one parenthesis
                for (int j = 0; j < curr.length(); j++) {
                    char c = curr.charAt(j);
                    if (c != '(' && c != ')') {
                        continue; // Only remove parentheses
                    }

                    String next = curr.substring(0, j) + curr.substring(j + 1);
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

            if (found) {
                break; // Minimum removals reached
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }
}