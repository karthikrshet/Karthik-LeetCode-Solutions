class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Step 1: Sort the array. This is the secret to avoiding duplicate combinations!
        Arrays.sort(candidates);
        
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int startIndex, int remainingTarget, List<Integer> currentComb, List<List<Integer>> result) {
        // Base case 1: Target reached
        if (remainingTarget == 0) {
            result.add(new ArrayList<>(currentComb));
            return;
        }

        // Try building combinations
        for (int i = startIndex; i < candidates.length; i++) {
            // Base case 2: If the current number is bigger than what we have left, 
            if (remainingTarget - candidates[i] < 0) {
                break; 
            }

            // The Duplicate Skipper: 
            
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Choose the number
            currentComb.add(candidates[i]);
            
            // Explore further: Note that we pass 'i + 1' this time!
            // This guarantees we only use each specific element from the array once.
            backtrack(candidates, i + 1, remainingTarget - candidates[i], currentComb, result);
            
            // Backtrack: Undo the choice to try the next option
            currentComb.remove(currentComb.size() - 1);
        }
    }
}