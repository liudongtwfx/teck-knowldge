package jl.lock;

import java.util.*;

public class AqsTest {
    public static void main(String[] args) {
        List<String> relations = Arrays.asList("A>B", "B>C", "C>D", "B>D", "B>E");
        List<String> arrays = Arrays.asList("A", "B", "C", "D", "E");
        arrays.sort(new AgeComparator(relations));
        arrays.forEach(System.out::println);

        List<String> testTwo = Arrays.asList("A", "B", "E", "D", "C");
        testTwo.sort(new AgeComparator(relations));
        testTwo.forEach(System.out::println);
    }


    private static class AgeComparator implements Comparator<String> {
        private static Map<String, Set<String>> ageElderMap = new HashMap<>();

        private static Map<String, Set<String>> ageYoungerMap = new HashMap<>();

        AgeComparator(List<String> relations) {
            Objects.requireNonNull(relations);
            for (String relation : relations) {
                String[] split = relation.split(">");
                if (split.length != 2) {
                    System.err.println("length not right");
                    continue;
                }
                Set<String> elder = ageElderMap.getOrDefault(split[0], new HashSet<>());
                elder.add(split[1]);
                ageElderMap.put(split[0], elder);
                ageYoungerMap.getOrDefault(split[0], Collections.emptySet()).forEach(eld -> ageElderMap.get(eld).add(split[1]));

                Set<String> younger = ageYoungerMap.getOrDefault(split[1], new HashSet<>());
                younger.add(split[0]);
                ageYoungerMap.put(split[1], younger);
                ageElderMap.getOrDefault(split[1], Collections.emptySet()).forEach(young -> ageYoungerMap.get(young).add(split[0]));
            }
        }

        @Override
        public int compare(String o1, String o2) {
            if (ageElderMap.getOrDefault(o1, Collections.emptySet()).contains(o2)) {
                return 1;
            }

            if (ageYoungerMap.getOrDefault(o1, Collections.emptySet()).contains(o2)) {
                return -1;
            }
            return 0;
        }
    }
}

