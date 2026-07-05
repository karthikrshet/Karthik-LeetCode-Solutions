class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int startIndex, int remainingTarget, List<Integer> currentComb, List<List<Integer>> result) {
        // Base case 1: Target reached
        if (remainingTarget == 0) {
            result.add(new ArrayList<>(currentComb));
            return;
        }
        
        // Base case 2: Target exceeded
        if (remainingTarget < 0) {
            return;
        }

        // Recursive exploration
        for (int i = startIndex; i < candidates.length; i++) {
            currentComb.add(candidates[i]);
            backtrack(candidates, i, remainingTarget - candidates[i], currentComb, result);
            currentComb.remove(currentComb.size() - 1);
        }
    }
}