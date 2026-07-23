import java.util.Arrays;

class Solution {
    public boolean asteroidsDestroyed(long mass, int[] asteroids) {
        // Sort the asteroids to process smaller ones first
        Arrays.sort(asteroids);
        
        for (int asteroid : asteroids) {
            if (mass < asteroid) {
                return false;
            }
            mass += asteroid; // Gain the mass of the destroyed asteroid
        }
        
        return true;
    }
}