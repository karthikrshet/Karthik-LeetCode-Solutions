class Solution:
    def remainingMethods(self, n: int, k: int, invocations: List[List[int]]) -> List[int]:
        from collections import defaultdict

        adj = defaultdict(list)
        for u, v in invocations:
            adj[u].append(v)
            
        # Step 1: Find all suspicious methods starting from k
        suspicious = set()
        stack = [k]
        suspicious.add(k)
        
        while stack:
            curr = stack.pop()
            for neighbor in adj[curr]:
                if neighbor not in suspicious:
                    suspicious.add(neighbor)
                    stack.append(neighbor)
                    
        # Step 2: Check if any method outside the suspicious set invokes any method inside it
        isolated = True
        for u, v in invocations:
            if u not in suspicious and v in suspicious:
                isolated = False
                break
                
        # Step 3: If isolated, remove all suspicious methods; otherwise, remove none
        if isolated:
            return [i for i in range(n) if i not in suspicious]
        else:
            return list(range(n))