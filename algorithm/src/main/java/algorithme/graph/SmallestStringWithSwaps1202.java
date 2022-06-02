package algorithme.graph;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

public class SmallestStringWithSwaps1202 {

    public static void main(String[] args) {
        new SmallestStringWithSwaps1202().bridge();
    }

    private static String getInputFromFile() {
        String filePath = "/Users/liudong/PycharmProjects/pythonProject/1.txt";
        StringBuilder sb = new StringBuilder();
        try (FileInputStream stream = new FileInputStream(new File(filePath))) {
            int i = 0;
            byte[] bytes = new byte[4096];
            while ((i = stream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void bridge() {
        String input = getInputFromFile();
        String[] strs = input.split("\n");
        List<List<Integer>> pairs = new Gson().fromJson(strs[1], new TypeToken<List<List<Integer>>>() {
        }.getType());
        String s = "dcab";
        long start = System.currentTimeMillis();
        System.out.println(new Solution().smallestStringWithSwaps(strs[0], pairs));
        System.out.println(System.currentTimeMillis() - start);
    }

    class Solution {
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            Map<Integer, Set<Integer>> map = new HashMap<>();
            for (List<Integer> pair : pairs) {
                putToMap(map, pair.get(0), pair.get(1));
                putToMap(map, pair.get(1), pair.get(0));
            }
            Set<Integer> added = new HashSet<>();
            int n = s.length();
            char[] origin = s.toCharArray();
            char[] chars = new char[n];
            long start = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                if (added.contains(i)) {
                    continue;
                }
                List<Integer> list = bfs(map, i);
                added.addAll(list);
                if (list.size() == 1) {
                    chars[list.get(0)] = origin[list.get(0)];
                    continue;
                }
                char[] tmpChars = new char[list.size()];
                for (int j = 0; j < list.size(); j++) {
                    tmpChars[j] = origin[list.get(j)];
                }
                Arrays.sort(tmpChars);
                for (int j = 0; j < list.size(); j++) {
                    chars[list.get(j)] = tmpChars[j];
                }
            }
            return new String(chars);
        }

        private List<Integer> bfs(Map<Integer, Set<Integer>> map, int start) {
            Queue<Integer> queue = new LinkedList<>();
            Set<Integer> list = new HashSet<>();
            list.add(start);
            queue.add(start);
            while (!queue.isEmpty()) {
                Integer first = queue.poll();
                Set<Integer> integers = map.getOrDefault(first, Collections.emptySet());
                for (Integer integer : integers) {
                    if (list.add(integer)) {
                        queue.offer(integer);
                    }
                }
            }
            return list.stream().sorted().collect(Collectors.toList());
        }

        private void putToMap(Map<Integer, Set<Integer>> map, Integer pKey, Integer sKey) {
            Set<Integer> tmp = map.get(pKey);
            if (tmp == null) {
                tmp = new HashSet<>();
                tmp.add(sKey);
                map.put(pKey, tmp);
                return;
            }
            tmp.add(sKey);
        }
    }

    class Solution1 {
        private int[] parent;
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            if (s == null || s.length() == 0) {
                return null;
            }
            parent = new int[s.length()];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }

            for (List<Integer> pair : pairs) {
                union(pair.get(0), pair.get(1));
            }

            Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
            char[] sChar = s.toCharArray();
            for (int i = 0; i < sChar.length; i++) {
                int root = find(i);
                map.putIfAbsent(root, new PriorityQueue<>());
                map.get(root).offer(sChar[i]);
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sChar.length; i++) {
                sb.append(map.get(find(i)).poll());
            }
            return sb.toString();
        }
        private int find(int index) {
            while (parent[index] != index) {
                parent[index] = parent[parent[index]];
                index = parent[index];
            }
            return index;
        }
        private void union(int a, int b) {
            int aParent = find(a);
            int bParent = find(b);
            if (aParent < bParent) {
                parent[bParent] = aParent;
            } else {
                parent[aParent] = bParent;
            }
        }
    }
}
