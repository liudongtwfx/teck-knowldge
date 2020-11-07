package algorithme.dp;

import java.util.ArrayList;
import java.util.List;

public class ProductOfTheLastKNumbers {
    public static void main(String[] args) {
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3);        // [3]
        productOfNumbers.add(0);        // [3,0]
        productOfNumbers.add(2);        // [3,0,2]
        productOfNumbers.add(5);        // [3,0,2,5]
        productOfNumbers.add(4);        // [3,0,2,5,4]
        productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
        productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
        productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
        productOfNumbers.add(8);        // [3,0,2,5,4,8]
        productOfNumbers.getProduct(2); //// return 32. The product of the last 2 numbers is 4 * 8 = 32
        System.out.println("I ams doing the sam");
        System.out.println("what is your nam");
    }

    static class ProductOfNumbers {
        private final List<Integer> dp = new ArrayList<>();
        int last = 1;

        public ProductOfNumbers() {

        }

        public void add(int num) {
            if (num == 0) {
                dp.clear();
                last = 1;
                return;
            }
            int tmpDp = last * num;
            last = tmpDp;
            dp.add(tmpDp);
        }

        public int getProduct(int k) {
            if (k > dp.size()) {
                return 0;
            }
            int ans = last;
            if (k == dp.size()) {
                return last;
            }
            int b = dp.get(dp.size() - k - 1);
            return b == 0 ? 0 : ans / b;
        }
    }
}
