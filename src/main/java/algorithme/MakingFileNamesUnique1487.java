package algorithme;

import java.util.HashMap;
import java.util.Map;

public class MakingFileNamesUnique1487 {
    public static void main(String[] args) {

    }

    class Solution {
        public String[] getFolderNames(String[] names) {
            Map<String, Integer> nameMaxMap = new HashMap<>();
            String[] ans = new String[names.length];
            for (int i = 0; i < names.length; i++) {
                String originName = names[i];
                if (!nameMaxMap.containsKey(originName)) {
                    ans[i] = originName;
                    nameMaxMap.put(originName, 1);
                    continue;
                }
                int currentNumber = nameMaxMap.get(originName);
                String current = originName + "(" + currentNumber + ")";
                while (nameMaxMap.containsKey(current)) {
                    currentNumber++;
                    current = originName + "(" + currentNumber + ")";
                }
                nameMaxMap.put(originName, currentNumber);
                nameMaxMap.put(current, 1);
                ans[i] = current;
            }
            return ans;
        }
    }
}
