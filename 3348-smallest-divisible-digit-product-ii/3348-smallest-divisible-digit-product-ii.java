import java.util.*;

class Solution {
    private static final int[] PRIMES = {2, 3, 5, 7};

    private int[] getPrimeCounts(long val) {
        int[] counts = new int[8];
        for (int p : PRIMES) {
            while (val % p == 0) {
                counts[p]++;
                val /= p;
            }
        }
        return counts;
    }

    private int[] getFactorCount(int[] c) {
        int c2 = c[2], c3 = c[3], c5 = c[5], c7 = c[7];
        int count8 = c2 / 3;
        int rem2 = c2 % 3;
        int count9 = c3 / 2;
        int rem3 = c3 % 2;
        
        int count4 = rem2 / 2;
        int count2 = rem2 % 2;
        
        int count6 = 0;
        if (count2 == 1 && rem3 == 1) {
            count2 = 0;
            rem3 = 0;
            count6 = 1;
        } else if (rem3 == 1 && count4 > 0) {
            count4--;
            count2 = 1;
            count6 = 1;
            rem3 = 0;
        }
        
        int[] factors = new int[10];
        factors[2] = count2;
        factors[3] = rem3;
        factors[4] = count4;
        factors[5] = c5;
        factors[6] = count6;
        factors[7] = c7;
        factors[8] = count8;
        factors[9] = count9;
        return factors;
    }

    private String construct(int[] factors) {
        StringBuilder sb = new StringBuilder();
        for (int d = 2; d <= 9; d++) {
            for (int k = 0; k < factors[d]; k++) {
                sb.append(d);
            }
        }
        return sb.toString();
    }

    private int sumValues(int[] factors) {
        int sum = 0;
        for (int d = 2; d <= 9; d++) sum += factors[d];
        return sum;
    }

    public String smallestNumber(String num, long t) {
        int[] req = getPrimeCounts(t);
        long tempT = t;
        for (int p : PRIMES) {
            while (tempT % p == 0) tempT /= p;
        }
        if (tempT > 1) return "-1";

        int[] factorCountT = getFactorCount(req);
        int n = num.length();
        int minLenReq = sumValues(factorCountT);

        // Helper to generate the length-expanded result properly
        String expandedResult = null;
        if (minLenReq > n) {
            expandedResult = construct(factorCountT);
        }

        int firstZero = num.indexOf('0');
        
        if (firstZero != -1) {
            int[] primeCountPrefix = new int[8];
            for (int k = 0; k < firstZero; k++) {
                int[] fc = getPrimeCounts(num.charAt(k) - '0');
                for (int p : PRIMES) primeCountPrefix[p] += fc[p];
            }
            
            for (int i = firstZero; i >= 0; i--) {
                if (i < firstZero) {
                    primeCountPrefix = new int[8];
                    for (int k = 0; k < i; k++) {
                        int[] fc = getPrimeCounts(num.charAt(k) - '0');
                        for (int p : PRIMES) primeCountPrefix[p] += fc[p];
                    }
                }
                
                int startD = (i < firstZero) ? (num.charAt(i) - '0' + 1) : 1;
                for (int biggerDigit = startD; biggerDigit <= 9; biggerDigit++) {
                    int[] dfc = getPrimeCounts(biggerDigit);
                    int[] neededPrimes = new int[8];
                    for (int p : PRIMES) {
                        neededPrimes[p] = Math.max(0, req[p] - primeCountPrefix[p] - dfc[p]);
                    }
                    int[] factorsAfter = getFactorCount(neededPrimes);
                    
                    int spaceAfter = n - 1 - i;
                    if (sumValues(factorsAfter) <= spaceAfter) {
                        int fillOnes = spaceAfter - sumValues(factorsAfter);
                        char[] ones = new char[fillOnes];
                        Arrays.fill(ones, '1');
                        String suffix = new String(ones) + construct(factorsAfter);
                        String prefixPart = (i < firstZero) ? num.substring(0, i) : num.substring(0, firstZero);
                        return prefixPart + biggerDigit + suffix;
                    }
                }
            }

            return expandedResult != null ? expandedResult : construct(factorCountT);
        }

        int[][] prefixPrimesList = new int[n + 1][8];
        for (int k = 0; k < n; k++) {
            System.arraycopy(prefixPrimesList[k], 0, prefixPrimesList[k + 1], 0, 8);
            int[] fc = getPrimeCounts(num.charAt(k) - '0');
            for (int p : PRIMES) prefixPrimesList[k + 1][p] += fc[p];
        }

        boolean satisfies = true;
        for (int p : PRIMES) {
            if (prefixPrimesList[n][p] < req[p]) {
                satisfies = false;
                break;
            }
        }
        if (satisfies) return num;

        for (int i = n - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';
            int[] currPrime = prefixPrimesList[i];
            int spaceAfter = n - 1 - i;
            
            for (int biggerDigit = d + 1; biggerDigit <= 9; biggerDigit++) {
                int[] dfc = getPrimeCounts(biggerDigit);
                int[] neededPrimes = new int[8];
                for (int p : PRIMES) {
                    neededPrimes[p] = Math.max(0, req[p] - currPrime[p] - dfc[p]);
                }
                int[] factorsAfter = getFactorCount(neededPrimes);
                
                if (sumValues(factorsAfter) <= spaceAfter) {
                    int fillOnes = spaceAfter - sumValues(factorsAfter);
                    char[] ones = new char[fillOnes];
                    Arrays.fill(ones, '1');
                    String suffix = new String(ones) + construct(factorsAfter);
                    return num.substring(0, i) + biggerDigit + suffix;
                }
            }
        }

        // If no valid suffix fits length N, we must expand length to N + 1 (or minLenReq)
        int targetLen = Math.max(n + 1, minLenReq);
        int fillOnes = targetLen - sumValues(factorCountT);
        char[] ones = new char[Math.max(0, fillOnes)];
        Arrays.fill(ones, '1');
        return new String(ones) + construct(factorCountT);
    }
}