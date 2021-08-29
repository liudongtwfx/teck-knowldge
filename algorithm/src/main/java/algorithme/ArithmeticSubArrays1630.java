package algorithme;

import org.springframework.scripting.groovy.GroovyScriptFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArithmeticSubArrays1630 {

    public static void main(String[] args) throws Exception {
        String jsonTest = "class A {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"hello world\");\n" +
                "    }\n" +
                "}";
        GroovyScriptFactory factory = new GroovyScriptFactory(jsonTest);
        Class<?> hello = factory.getGroovyClassLoader().loadClass("Hello");
    }

    class Solution {
        public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
            List<Boolean> ans = new ArrayList<>();
            for (int i = 0; i < l.length; i++) {
                boolean isArithmetic = true;
                int[] tmpArray = Arrays.copyOfRange(nums, l[i], r[i] + 1);
                Arrays.sort(tmpArray);
                int tmp = tmpArray[1] - tmpArray[0];
                for (int j = 1; j < tmpArray.length - 1; j++) {
                    if (tmpArray[j + 1] - tmpArray[j] != tmp) {
                        isArithmetic = false;
                        break;
                    }
                }
                ans.add(isArithmetic);
            }
            return ans;
        }
    }
}
