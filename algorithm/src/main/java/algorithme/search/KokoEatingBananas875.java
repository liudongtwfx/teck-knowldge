package algorithme.search;

import com.google.gson.Gson;

import java.io.FileInputStream;

public class KokoEatingBananas875 {
    public static void main(String[] args) {
        new KokoEatingBananas875().bridge();
    }

    private void bridge() {
        int[] piles = new int[0];
        try {
            piles = getInputFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int h = 63939633;
        System.out.println(new Solution().minEatingSpeed(piles, h));
    }

    private int[] getInputFromFile() throws Exception {
        FileInputStream fi = new FileInputStream("/Users/liudong/1.txt");
        byte[] bytes = new byte[1024];
        StringBuilder sb = new StringBuilder();
        int len = 0;
        while ((len = fi.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len));
        }
        System.out.println(sb);
        return new Gson().fromJson(sb.toString(), int[].class);
    }

    class Solution {
        private int count = 0;

        public int minEatingSpeed(int[] piles, int h) {
            int result = binarySearch(piles, h, 1, 1000000001);
            System.out.println(count);
            return result;
        }

        private int binarySearch(int[] piles, int h, int start, int end) {
            if (start >= end) {
                return -1;
            }
            if (end - start <= 2) {
                for (int i = start; i <= end; i++) {
                    if (satisfy(piles, h, i)) {
                        return i;
                    }
                }
                return -1;
            }
            int mid = (start + end) / 2;
            if (satisfy(piles, h, mid)) {
                return binarySearch(piles, h, start, mid);
            }
            return binarySearch(piles, h, mid + 1, end);
        }

        private boolean satisfy(int[] piles, int h, int k) {
            count++;
            if (k == 0) {
                return false;
            }
            int total = 0;
            for (int pile : piles) {
                total += (pile % k == 0 ? pile / k : pile / k + 1);
                if (total > h) {
                    return false;
                }
            }
            return true;
        }
    }
}
