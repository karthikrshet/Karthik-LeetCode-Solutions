class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        // This string contains all possible sequential digits
        String sample = "123456789";
        
        // The constraints say 'low' is at least 10, so minimum length is 2.
        // The max sequential number is 123456789, so maximum length is 9.
        for (int length = 2; length <= 9; length++) {
            // Slide a window of 'length' across our sample string
            for (int i = 0; i <= 9 - length; i++) {
                String sub = sample.substring(i, i + length);
                int num = Integer.parseInt(sub);
                
                // If it falls within our range, add it to the list!
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}