class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int currentGas = 0;
        int startIndex = 0;
        
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            
            // Add the net gas (gained - spent) at this station to our current tank
            currentGas += gas[i] - cost[i];
            
            // If the tank drops below zero, we cannot reach the next station
            if (currentGas < 0) {
                // The current station and all previous ones since startIndex are invalid
                startIndex = i + 1;
                // Reset the tank for the new starting point
                currentGas = 0;
            }
        }
        
        // If we don't have enough gas overall to cover the total cost, it's impossible
        if (totalGas < totalCost) {
            return -1;
        }
        
        // A solution is guaranteed to exist, so our calculated startIndex is correct
        return startIndex;
    }
}