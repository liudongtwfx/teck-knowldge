package algorithme.twopointer;

import java.util.Arrays;

public class BoatsToSavePeople331 {
    public static void main(String[] args) {
        new BoatsToSavePeople331().bridge();
    }

    private void bridge() {
        int[] people = {2, 2, 2, 6, 6, 7, 10, 10, 10, 11, 12, 13, 18, 22, 26, 33, 41, 47, 49, 50};
        int limit = 50;
        Arrays.sort(people);
        System.out.println(Arrays.toString(people));
        System.out.println(new Solution().numRescueBoats(people, limit));
    }

    class Solution {
        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
            int i, j;
            for (i = 0, j = people.length - 1; i <= j; --j)
                if (people[i] + people[j] <= limit) ++i;
            return people.length - 1 - j;
        }
    }
}
