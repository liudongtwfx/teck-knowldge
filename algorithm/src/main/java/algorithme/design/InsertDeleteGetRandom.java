package algorithme.design;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

@Slf4j
public class InsertDeleteGetRandom {
    public static void main(String[] args) throws Exception {
        new InsertDeleteGetRandom().bridge();
    }

    private void bridge() throws Exception {
        String methods = "[\"RandomizedSet\",\"insert\",\"insert\",\"remove\",\"insert\",\"remove\",\"getRandom\"]";
        String args = "[[],[0],[1],[0],[2],[1],[]]";
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> methodList = gson.fromJson(methods, type);
        Type argsType = new TypeToken<List<List<Integer>>>() {
        }.getType();
        List<List<Integer>> argsList = gson.fromJson(args, argsType);

        RandomizedSet randomizedSet = new RandomizedSet();
        for (int i = 1; i < methodList.size(); i++) {
            try {
                Class[] argsTypes = "getRandom".equals(methodList.get(i)) ? null : new Class[]{int.class};
                Method method = RandomizedSet.class.getMethod(methodList.get(i), argsTypes);
                Object value = method.invoke(randomizedSet, argsList.get(i).toArray());
                System.out.println(value);
            } catch (Exception e) {
                log.info("method:{},args:{}", methodList.get(i), argsList.get(i), e);
            }
        }
    }

    class RandomizedSet {
        private final LinkedHashMap<Integer, Integer> valueMap;
        private final List<Integer> valueList;

        public RandomizedSet() {
            valueMap = new LinkedHashMap<>();
            valueList = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (valueMap.containsKey(val)) {
                return false;
            }
            valueList.add(val);
            valueMap.put(val, valueList.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            int ind = valueMap.getOrDefault(val, -1);
            if (ind == -1) {
                return false;
            }
            Collections.swap(valueList, ind, valueList.size() - 1);
            int swappedWith = valueList.get(ind);
            valueMap.put(swappedWith, ind);
            valueList.remove(valueList.size() - 1);
            valueMap.remove(val);
            return true;
        }

        public int getRandom() {
            int random = new Random().nextInt(valueList.size());
            return valueList.get(random);
        }
    }
}
