from collections import defaultdict, deque
from typing import List

class Solution:
    def findLadders(self, beginWord: str, endWord: str, wordList: List[str]) -> List[List[str]]:
        word_set = set(wordList)
        if endWord not in word_set:
            return []
        
        # Maps word -> list of previous words in shortest paths
        parents = defaultdict(list)
        distance = {beginWord: 0}
        
        queue = deque([beginWord])
        found = False
        
        # Step 1: BFS to find shortest paths and build parent graph
        while queue and not found:
            visited_in_level = set()
            for _ in range(len(queue)):
                curr = queue.popleft()
                curr_dist = distance[curr]
                
                if curr == endWord:
                    found = True
                    continue
                
                # Generate all possible single-letter transformations
                for i in range(len(curr)):
                    for c in 'abcdefghijklmnopqrstuvwxyz':
                        next_word = curr[:i] + c + curr[i+1:]
                        
                        if next_word in word_set:
                            if next_word not in distance:
                                distance[next_word] = curr_dist + 1
                                queue.append(next_word)
                                parents[next_word].append(curr)
                                visited_in_level.add(next_word)
                            elif distance[next_word] == curr_dist + 1:
                                parents[next_word].append(curr)
                                
            # Remove words visited in this level to prevent cross-edges
            word_set -= visited_in_level

        if not found:
            return []

        # Step 2: Backtracking (DFS) to reconstruct paths from endWord to beginWord
        res = []
        
        def dfs(current_word: str, path: List[str]):
            if current_word == beginWord:
                res.append(path[::-1])
                return
            
            for p in parents[current_word]:
                path.append(p)
                dfs(p, path)
                path.pop()

        dfs(endWord, [endWord])
        return res