class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        
        for (int i = 0; i < secret.length(); i++) {
            char sChar = secret.charAt(i);
            char gChar = guess.charAt(i);
            
            if (sChar == gChar) {
                bulls++;
            } else {
                if (numbers[sChar - '0'] < 0) {
                    cows++;
                }
                if (numbers[gChar - '0'] > 0) {
                    cows++;
                }
                numbers[sChar - '0']++;
                numbers[gChar - '0']--;
            }
        }
        
        return bulls + "A" + cows + "B";
    }
}