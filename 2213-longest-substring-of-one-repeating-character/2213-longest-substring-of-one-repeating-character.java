class Solution {
    int[] mx, lmx, rmx;
    char[] charArr;

    private void pushup(int u, int l, int r) {
        int mid = (l + r) / 2;
        int left = u * 2, right = u * 2 + 1;
        
        mx[u] = Math.max(mx[left], mx[right]);
        lmx[u] = lmx[left];
        rmx[u] = rmx[right];
        
        if (charArr[mid] == charArr[mid + 1]) {
            mx[u] = Math.max(mx[u], rmx[left] + lmx[right]);
            if (lmx[left] == mid - l + 1) {
                lmx[u] = lmx[left] + lmx[right];
            }
            if (rmx[right] == r - mid) {
                rmx[u] = rmx[right] + rmx[left];
            }
        }
    }

    private void build(int u, int l, int r) {
        if (l == r) {
            mx[u] = lmx[u] = rmx[u] = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(u * 2, l, mid);
        build(u * 2 + 1, mid + 1, r);
        pushup(u, l, r);
    }

    private void update(int u, int l, int r, int idx) {
        if (l == r) {
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) {
            update(u * 2, l, mid, idx);
        } else {
            update(u * 2 + 1, mid + 1, r, idx);
        }
        pushup(u, l, r);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        charArr = s.toCharArray();
        
        mx = new int[4 * n];
        lmx = new int[4 * n];
        rmx = new int[4 * n];
        
        build(1, 0, n - 1);
        
        int k = queryIndices.length;
        int[] ans = new int[k];
        
        for (int i = 0; i < k; i++) {
            char ch = queryCharacters.charAt(i);
            int idx = queryIndices[i];
            
            if (charArr[idx] == ch) {
                ans[i] = mx[1];
                continue;
            }
            
            charArr[idx] = ch;
            update(1, 0, n - 1, idx);
            ans[i] = mx[1];
        }
        
        return ans;
    }
}