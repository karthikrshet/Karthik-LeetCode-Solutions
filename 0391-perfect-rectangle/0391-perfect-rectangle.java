
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0) return false;

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        long totalArea = 0;
        Set<String> corners = new HashSet<>();

        for (int[] rect : rectangles) {
            int x1 = rect[0], y1 = rect[1], x2 = rect[2], y2 = rect[3];

            minX = Math.min(minX, x1);
            minY = Math.min(minY, y1);
            maxX = Math.max(maxX, x2);
            maxY = Math.max(maxY, y2);

            totalArea += (long) (x2 - x1) * (y2 - y1);

            // Define the 4 corners of the current rectangle
            String p1 = x1 + "," + y1;
            String p2 = x1 + "," + y2;
            String p3 = x2 + "," + y1;
            String p4 = x2 + "," + y2;

            // Toggle presence in the set (add if missing, remove if present)
            for (String p : new String[]{p1, p2, p3, p4}) {
                if (!corners.add(p)) {
                    corners.remove(p);
                }
            }
        }

        // Check if the remaining corners in the set are exactly the 4 outer corners of the bounding box
        if (corners.size() != 4 || 
            !corners.contains(minX + "," + minY) || 
            !corners.contains(minX + "," + maxY) || 
            !corners.contains(maxX + "," + minY) || 
            !corners.contains(maxX + "," + maxY)) {
            return false;
        }

        // Check if the total area matches the bounding box area
        long expectedArea = (long) (maxX - minX) * (maxY - minY);
        return totalArea == expectedArea;
    }
}