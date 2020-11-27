package algorithme.array;

public class FurthestBuildingYouCanReach {
    public static void main(String[] args) {

    }

    class Solution {
        public int furthestBuilding(int[] heights, int bricks, int ladders) {
            int last = Integer.MAX_VALUE;
            for (int i = 0; i < heights.length; i++) {
                int current = heights[i];
                if (last < current) {

                    if (ladders <= 0 || bricks < current - last) {
                        return current - 1;
                    }
                }
                last = current;
            }
            return heights.length - 1;
        }
    }
}
