import java.util.Arrays;

class Solution {
    public String largestNumber(int[] nums) {
        String[] sNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sNums[i] = String.valueOf(nums[i]);
        }
        
        // Custom sort: compare concatenated combinations (b + a) vs (a + b)
        Arrays.sort(sNums, (a, b) -> (b + a).compareTo(a + b));
        
        // Edge case: if the highest number is "0", the entire number is "0"
        if (sNums[0].equals("0")) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for (String s : sNums) {
            sb.append(s);
        }
        
        return sb.toString();
    }
}