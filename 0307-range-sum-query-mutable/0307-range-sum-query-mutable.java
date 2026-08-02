class NumArray {
    private int[] tree;
    private int[] nums;
    private int n;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.tree = new int[n + 1];
        
        // Initialize Fenwick Tree with the given array values
        for (int i = 0; i < n; i++) {
            init(i + 1, nums[i]);
        }
    }
    
    private void init(int i, int delta) {
        while (i <= n) {
            tree[i] += delta;
            i += i & -i; // Move to the parent / next update node
        }
    }
    
    public void update(int index, int val) {
        int delta = val - nums[index];
        nums[index] = val;
        init(index + 1, delta);
    }
    
    private int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & -i; // Move to the previous range sum node
        }
        return sum;
    }
    
    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */