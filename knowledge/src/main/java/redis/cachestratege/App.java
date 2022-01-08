package redis.cachestratege;

import java.util.Objects;

/***
 * <a href='https://www.cnblogs.com/mrcharleshu/p/13193505.html'>参考文档 </a>
 */
public class App {
    class Solution {
        public String multiply(String num1, String num2) {
            if (Objects.equals(num1, "0") || Objects.equals(num2, "0")) {
                return "0";
            }
            StringBuilder res = new StringBuilder();
            for (int i = num1.length() - 1; i >= 0; i--) {
                for (int j = num1.length() - 1 - i, cnt = num2.length() - 1; cnt >= 0; cnt--, j++) {
                    int resTemp = (num1.charAt(i) - '0') * (num2.charAt(cnt) - '0');
                    int a = resTemp / 10;
                    int b = resTemp % 10;
                    if (j >= res.length()) {
                        if (resTemp >= 10) {
                            res.append(b + '0');
                            res.append(a + '0');
                        } else {
                            res.append(resTemp + '0');
                        }
                    } else {
                        int temp = res.charAt(j) - '0' + b;
                        res.setCharAt(j, (char) (temp % 10 + '0'));
                        a += temp / 10;
                        int x = j + 1;
                        while (a != 0) {
                            if (x == res.length()) {
                                res.append(a % 10 + '0');
                                a = a / 10;
                            } else {
                                temp = res.charAt(x) - '0' + a;
                                res.setCharAt(x, (char) (temp % 10 + '0'));
                                a = temp / 10;
                            }
                            x++;
                        }
                    }
                }
            }
            return res.reverse().toString();
        }
    }
}
