import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                
                if (curr.equals(endWord)) {
                    return level;
                }
                
                char[] chars = curr.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char originalChar = chars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (chars[j] == c) continue;
                        
                        chars[j] = c;
                        String nextWord = new String(chars);
                        
                        if (wordSet.contains(nextWord)) {
                            wordSet.remove(nextWord);
                            queue.offer(nextWord);
                        }
                    }
                    chars[j] = originalChar;
                }
            }
            level++;
        }
        
        return 0;
    }
}