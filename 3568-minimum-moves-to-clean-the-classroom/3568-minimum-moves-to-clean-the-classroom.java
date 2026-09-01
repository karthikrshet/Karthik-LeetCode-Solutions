import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energyCap) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1, startC = -1;
        List<int[]> litters = new ArrayList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litters.add(new int[]{r, c});
                }
            }
        }

        int totalLitters = litters.size();
        if (totalLitters == 0) return 0;

        Map<Long, Integer> litterMap = new HashMap<>();
        for (int i = 0; i < totalLitters; i++) {
            int[] l = litters.get(i);
            litterMap.put(((long) l[0] << 32) | l[1], i);
        }

        int initialMask = 0;
        long startKey = ((long) startR << 32) | startC;
        if (litterMap.containsKey(startKey)) {
            initialMask |= (1 << litterMap.get(startKey));
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[m][n][energyCap + 1][1 << totalLitters];

        queue.offer(new int[]{startR, startC, energyCap, initialMask, 0});
        visited[startR][startC][energyCap][initialMask] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], energy = curr[2], mask = curr[3], moves = curr[4];

            if (mask == (1 << totalLitters) - 1) {
                return moves;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                char nextChar = classroom[nr].charAt(nc);
                if (nextChar == 'X') continue;

                int nextEnergy = energy - 1;
                int nextMask = mask;
                long key = ((long) nr << 32) | nc;
                if (litterMap.containsKey(key)) {
                    int idx = litterMap.get(key);
                    nextMask |= (1 << idx);
                }

                if (nextChar == 'R') {
                    nextEnergy = energyCap;
                } else if (nextEnergy == 0) {
                    // Energy reaches 0. We can only step here if it completes all remaining litters,
                    // since we cannot make any further moves from a non-R cell with 0 energy.
                    if (nextMask != (1 << totalLitters) - 1) {
                        continue;
                    }
                }

                if (!visited[nr][nc][nextEnergy][nextMask]) {
                    visited[nr][nc][nextEnergy][nextMask] = true;
                    queue.offer(new int[]{nr, nc, nextEnergy, nextMask, moves + 1});
                }
            }
        }

        return -1;
    }
}