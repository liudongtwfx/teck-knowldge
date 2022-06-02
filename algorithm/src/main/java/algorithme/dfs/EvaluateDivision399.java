package algorithme.dfs;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.*;

public class EvaluateDivision399 {

    public static void main(String[] args) {
        new EvaluateDivision399().bridge();
    }

    private void bridge() {
        String equationsText = "[[\"a\",\"b\"],[\"b\",\"c\"]]\n";
        String valueText = "[2.0,3.0]\n";
        String queriesText = "[[\"a\",\"c\"],[\"b\",\"a\"],[\"a\",\"e\"],[\"a\",\"a\"],[\"x\",\"x\"]]";
        List<List<String>> equations = new Gson().fromJson(equationsText, new TypeToken<List<List<String>>>() {
        }.getType());
        double[] values = new Gson().fromJson(valueText, new TypeToken<double[]>() {
        }.getType());
        List<List<String>> queryies = new Gson().fromJson(queriesText, new TypeToken<List<List<String>>>() {
        }.getType());

        System.out.println(Arrays.toString(new Solution().calcEquation(equations, values, queryies)));
    }

    class Solution {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            Map<String, Map<String, Double>> ansMap = new LinkedHashMap<>();
            for (int i = 0; i < equations.size(); i++) {
                putToMap(ansMap, equations.get(i).get(0), equations.get(i).get(0), 1D);
                putToMap(ansMap, equations.get(i).get(1), equations.get(i).get(1), 1D);
                putToMap(ansMap, equations.get(i).get(0), equations.get(i).get(1), values[i]);
                putToMap(ansMap, equations.get(i).get(1), equations.get(i).get(0), 1 / values[i]);
            }
            ansMap.forEach((k, v) -> dfs(ansMap, k, k, new HashSet<>(), 1D));
            double[] ans = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                ans[i] = ansMap.getOrDefault(queries.get(i).get(0), Collections.emptyMap()).getOrDefault(queries.get(i).get(1), -1D);
            }
            return ans;
        }

        private void dfs(Map<String, Map<String, Double>> ansMap, String pKey, String sKey, Set<String> visited, double relation) {
            if (visited.contains(sKey)) {
                return;
            }
            visited.add(sKey);
            Map<String, Double> valueMap = ansMap.get(pKey);
            valueMap.put(sKey, relation);
            for (Map.Entry<String, Double> entry : new HashMap<>(ansMap.get(sKey)).entrySet()) {
                dfs(ansMap, pKey, entry.getKey(), visited, entry.getValue() * relation);
            }
        }


        private void putToMap(Map<String, Map<String, Double>> ansMap, String pKey, String sKey, Double value) {
            Map<String, Double> valueMap = ansMap.get(pKey);
            if (valueMap != null) {
                valueMap.put(sKey, value);
                return;
            }
            valueMap = new HashMap<>();
            valueMap.put(sKey, value);
            ansMap.put(pKey, valueMap);
        }
    }
}
