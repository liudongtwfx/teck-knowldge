package algorithme;

import java.util.Arrays;

public class SmallestStringWithAGivenNumericValue {
    public static void main(String[] args) {

    }

    class Solution {
        public String getSmallestString(int n, int k) {
            // a+z=n-1
            // a+26z+m=k ==> 25z+m=k-n+1

            int otherIndex = (k - n + 1) % 25 == 0 ? 25 : (k - n + 26) % 25;
            int z = (k - n + 1 - otherIndex) / 25;
            int a = n - 1 - z;

            char[] chars = new char[n];
            Arrays.fill(chars, 0, a + 1, 'a');
            Arrays.fill(chars, a + 1, a + 2, (char) ('a' + otherIndex - 1));
            Arrays.fill(chars, n - z, n, 'z');
            return new String(chars);
        }
    }
}
