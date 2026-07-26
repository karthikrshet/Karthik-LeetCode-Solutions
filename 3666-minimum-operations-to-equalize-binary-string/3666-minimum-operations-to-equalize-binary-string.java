import java.util.*;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        TreeSet<Integer>[] ts = new TreeSet[2];
        Arrays.setAll(ts, i -> new TreeSet<>());
        
        for (int i = 0; i <= n; i++) {
            ts[i % 2].add(i);
        }
        
        int cnt0 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                cnt0++;
            }
        }
        
        ts[cnt0 % 2].remove(cnt0);
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(cnt0);
        int ans = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int step = 0; step < size; step++) {
                int cur = q.poll();
                if (cur == 0) {
                    return ans;
                }
                
                int ones = n - cur;
                int xMin = Math.max(0, k - ones);
                int xMax = Math.min(cur, k);
                
                int zMin = cur + k - 2 * xMax;
                int zMax = cur + k - 2 * xMin;
                
                TreeSet<Integer> t = ts[zMin % 2];
                Integer next = t.ceiling(zMin);
                
                while (next != null && next <= zMax) {
                    q.offer(next);
                    t.remove(next);
                    next = t.ceiling(zMin);
                }
            }
            ans++;
        }
        
        return -1;
    }
}