package algorithme.design;

import java.util.*;

public class OperationsOnTree {
    public static void main(String[] args) {
        Map<Integer, String> nMap = new HashMap<>();
        Set<Integer> nums = new HashSet<>();
        List<Integer> numsList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            nums.add(i);
            nMap.put(i, String.valueOf(i));
            numsList.add(i);
        }
        long start = System.currentTimeMillis();
        boolean exists = nums.stream().allMatch(nMap::containsKey);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        exists = numsList.stream().allMatch(nMap::containsKey);
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }

    class LockingTree {
        private Map<Integer, Set<Integer>> ancestorsMap = new HashMap<>();
        private Map<Integer, Set<Integer>> descendantsMap = new HashMap<>();
        private Map<Integer, Integer> lockedCache = new HashMap<>();

        public LockingTree(int[] parent) {
            for (int i = 0; i < parent.length; i++) {
                Set<Integer> ancestors = processEachNode(parent, i);
                ancestorsMap.put(i, ancestors);
            }
        }

        public boolean lock(int num, int user) {
            Integer userCached = lockedCache.get(num);
            if (userCached != null) {
                return false;
            }
            lockedCache.put(num, user);
            return true;
        }

        public boolean unlock(int num, int user) {
            Integer userCached = lockedCache.get(num);
            if (userCached != null && userCached == user) {
                lockedCache.remove(num);
                return true;
            }
            return false;
        }

        public boolean upgrade(int num, int user) {
            Integer userCached = lockedCache.get(num);
            if (userCached != null && userCached != user) {
                return false;
            }
            Set<Integer> ancestors = ancestorsMap.get(num);
            // System.out.println(ancestors.stream().map(Object::toString).collect(Collectors.joining(",")));
            boolean allAncestors = ancestors.stream().noneMatch(lockedCache::containsKey);
            // System.out.println(existAncestors);
            if (!allAncestors) {
                return false;
            }
            Set<Integer> descendants = descendantsMap.get(num);
            boolean existDescendants = descendants != null && intersects(descendants, lockedCache.keySet());
            // System.out.println(descendants);
            if (!existDescendants) {
                return false;
            }
            descendants.forEach(descendant -> lockedCache.remove(descendant));
            lockedCache.put(num, user);
            return true;
        }

        private boolean intersects(Set<Integer> left, Set<Integer> right) {
            if (left.size() < right.size()) {
                for (Integer integer : left) {
                    if (right.contains(integer)) {
                        return true;
                    }
                }
                return false;
            }
            for (Integer integer : right) {
                if (left.contains(integer)) {
                    return true;
                }
            }
            return false;
        }

        private Set<Integer> processEachNode(int[] parent, int i) {
            Set<Integer> ancestors = new HashSet<>();
            int current = i;
            while (current != -1) {
                int parentIndex = parent[current];
                Set<Integer> descendants = descendantsMap.getOrDefault(parentIndex, new HashSet<>());
                descendants.add(i);
                descendantsMap.put(parentIndex, descendants);
                ancestors.add(parent[current]);
                current = parent[current];
            }
            return ancestors;
        }
    }
}
