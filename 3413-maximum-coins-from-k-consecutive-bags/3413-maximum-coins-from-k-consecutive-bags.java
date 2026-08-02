
class Solution {
    public long maximumCoins(int[][] coins, int k) {
        int[][] negatedCoins = negateLeftRight(coins);
        return Math.max(slide(coins, k), slide(negatedCoins, k));
    }

    private int[][] negateLeftRight(int[][] coins) {
        int[][] res = new int[coins.length][3];
        for (int i = 0; i < coins.length; ++i) {
            int l = coins[i][0];
            int r = coins[i][1];
            int c = coins[i][2];
            res[i][0] = -r;
            res[i][1] = -l;
            res[i][2] = c;
        }
        return res;
    }

    private long slide(int[][] coins, int k) {
        Arrays.sort(coins, Comparator.comparingInt(a -> a[0]));
        long maxCoins = 0;
        long windowSum = 0;
        int j = 0;

        for (int i = 0; i < coins.length; i++) {
            int li = coins[i][0];
            int ri = coins[i][1];
            long ci = coins[i][2];
            long rightBoundary = (long) li + k;

            // Accumulate intervals that fit entirely within [li, li + k)
            while (j + 1 < coins.length && coins[j + 1][0] < rightBoundary) {
                long lj = coins[j][0];
                long rj = coins[j][1];
                long cj = coins[j][2];
                windowSum += (long) (rj - lj + 1) * cj;
                j++;
            }

            // Calculate partial overlap for the current index `j`
            long last = 0;
            if (j < coins.length && coins[j][0] < rightBoundary) {
                long lj = coins[j][0];
                long rj = coins[j][1];
                long cj = coins[j][2];
                last = (long) (Math.min(rightBoundary - 1, rj) - lj + 1) * cj;
            }

            maxCoins = Math.max(maxCoins, windowSum + last);

            // Subtract the contribution of the starting interval before sliding forward
            windowSum -= (long) (ri - li + 1) * ci;
        }

        return maxCoins;
    }
}