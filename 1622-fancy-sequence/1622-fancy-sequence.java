import java.util.ArrayList;
import java.util.List;

class Fancy {
    private static final long MOD = 1_000_000_007;
    private List<Long> list;
    private long mult;
    private long add;

    public Fancy() {
        list = new ArrayList<>();
        mult = 1;
        add = 0;
    }
    
    public void append(int val) {
        // To reverse current operations: (val - add) * mult^(-1) % MOD
        long invMult = power(mult, MOD - 2);
        long originalVal = ((val - add % MOD + MOD) % MOD * invMult) % MOD;
        list.add(originalVal);
    }
    
    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }
    
    public void multAll(int m) {
        mult = (mult * m) % MOD;
        add = (add * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if (idx >= list.size()) {
            return -1;
        }
        long originalVal = list.get(idx);
        long result = (originalVal * mult + add) % MOD;
        return (int) result;
    }
    
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}