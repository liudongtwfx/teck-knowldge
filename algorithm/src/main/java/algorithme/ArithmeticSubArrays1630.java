package algorithme;

import org.springframework.scripting.groovy.GroovyScriptFactory;

import java.util.*;

public class ArithmeticSubArrays1630 {

    public static void main(String[] args) throws Exception {
        String jsonTest = "class Hello {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"hello world\");\n" +
                "    }\n" +
                "}";
        GroovyScriptFactory factory = new GroovyScriptFactory(jsonTest);
        groovy.lang.GroovyClassLoader groovyClassLoader = factory.getGroovyClassLoader();
        Class<?> hello = groovyClassLoader.loadClass("Hello");
    }

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] num : nums1) {
            map.put(num[0], num[1]);
        }
        for (int[] n : nums2) {
            map.merge(n[0], n[1], Integer::sum);
        }
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        int[][] res = new int[keys.size()][2];
        for (int i = 0; i < keys.size(); i++) {
            res[i] = new int[]{keys.get(i), map.get(keys.get(i))};
        }
        return res;
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
