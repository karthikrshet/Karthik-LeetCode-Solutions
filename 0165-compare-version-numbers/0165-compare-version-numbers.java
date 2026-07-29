class Solution {
    public int compareVersion(String version1, String version2) {
        // Split strings by the dot character
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        // Find the maximum length to iterate through
        int length = Math.max(v1.length, v2.length);
        
        for (int i = 0; i < length; i++) {
            // Parse the integer, or default to 0 if the array is exhausted
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            
            // Compare the parsed revisions
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
        }
        
        // If we reach this point, the versions are equal
        return 0;
    }
}