/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return       -1 if num is higher than the picked number
 *                1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        
        while (low <= high) {
            // Prevent potential integer overflow
            int mid = low + (high - low) / 2;
            int res = guess(mid);
            
            if (res == 0) {
                return mid; // Found the correct number
            } else if (res < 0) {
                high = mid - 1; // Target is lower than mid
            } else {
                low = mid + 1; // Target is higher than mid
            }
        }
        
        return -1; // Should never be reached given constraints
    }
}