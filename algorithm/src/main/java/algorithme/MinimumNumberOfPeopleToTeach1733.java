package algorithme;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumNumberOfPeopleToTeach1733 {
    public static void main(String[] args) {
        new MinimumNumberOfPeopleToTeach1733().bridge();
    }

    private void bridge() {
        int n = 23;
        int[][] languages = {{14, 7, 16, 22, 23}, {16, 17, 1}, {13, 15, 3, 5, 9, 10, 8, 6, 7, 23, 16, 12, 17, 4, 19, 2}, {5, 11, 12}, {13, 23, 17, 9, 3, 21, 15, 1, 16, 18, 7, 19, 14, 11, 5, 6, 8, 10, 4, 22}, {5, 9, 15, 14, 6, 21, 16, 3, 19, 1}, {16, 19, 21, 6, 5, 2, 8, 10, 20, 12, 9, 22, 13, 3, 1, 23, 11, 18, 14}, {19, 12, 21, 8, 22, 7, 17, 2, 16, 4, 5, 20, 15, 3, 1, 6, 18, 10, 23, 9, 14, 13, 11}, {1, 7, 17, 4, 11, 2, 3, 5, 8}, {10, 20, 14, 4, 11, 7}, {23, 11, 15}, {14, 9, 21, 7, 19, 18, 4, 20, 10, 15, 17, 3, 23, 12, 6, 13}, {2}, {9, 7, 2, 10, 20, 3, 21, 22}};
        int[][] friendShips = {{7, 13}, {7, 10}, {6, 13}, {4, 8}, {4, 7}, {2, 3}, {1, 3}, {5, 7}, {1, 6}, {5, 11}, {7, 12}, {4, 9}, {3, 12}, {10, 12}, {2, 8}, {5, 8}, {7, 8}, {1, 4}, {5, 10}, {9, 12}, {8, 10}, {1, 9}, {2, 10}, {6, 8}, {8, 13}, {4, 11}, {3, 9}, {6, 12}, {5, 6}, {8, 9}, {3, 13}, {3, 6}, {5, 12}, {11, 12}, {1, 11}, {4, 5}, {2, 7}, {6, 10}, {8, 12}, {3, 5}, {9, 10}, {10, 13}, {1, 5}, {1, 2}, {12, 13}, {1, 13}, {7, 11}, {4, 12}, {5, 9}, {2, 11}, {2, 4}, {1, 8}, {2, 13}, {10, 11}, {2, 9}, {9, 13}, {8, 11}, {2, 5}, {2, 6}, {3, 8}, {7, 9}, {6, 11}, {9, 11}, {3, 10}};
        System.out.println(new Solution().minimumTeachings(n, languages, friendShips));
    }

    class Solution {
        public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
            Map<Integer, Set<Integer>> friendMap = new HashMap<>();
            Map<Integer, Set<Integer>> languageMap = new HashMap<>();
            for (int i = 0; i < languages.length; i++) {
                Set<Integer> languageSet = Arrays.stream(languages[i]).boxed().collect(Collectors.toSet());
                languageMap.put(i + 1, languageSet);
            }
            for (int i = 0; i < friendships.length; i++) {
                addRelationShipMap(friendships[i][0], friendships[i][1], friendMap);
                addRelationShipMap(friendships[i][1], friendships[i][0], friendMap);
            }
            int min = languages.length;
            for (int i = 1; i <= n; i++) {
                int ans = 0;
                Map<Integer, Set<Integer>> tmpLanguageMap = new HashMap<>();
                for (Map.Entry<Integer, Set<Integer>> integerSetEntry : languageMap.entrySet()) {
                    tmpLanguageMap.put(integerSetEntry.getKey(), new HashSet<>(integerSetEntry.getValue()));
                }
                for (int j = 1; j <= languages.length; j++) {
                    Set<Integer> friends = friendMap.getOrDefault(j, new HashSet<>());
                    List<Set<Integer>> languageList = new ArrayList<>();
                    for (Integer friend : friends) {
                        languageList.add(tmpLanguageMap.get(friend));
                    }
                    ans += deduceAndAdd(languageList, tmpLanguageMap.get(j), j, i);
                }
                min = Math.min(min, ans);
            }
            return min;
        }

        private void addRelationShipMap(int left, int right, Map<Integer, Set<Integer>> friendShipMap) {
            Set<Integer> friendSet = friendShipMap.getOrDefault(left, new HashSet<>());
            friendSet.add(right);
            friendShipMap.put(left, friendSet);
        }

        private int deduceAndAdd(List<Set<Integer>> friendListLanguage, Set<Integer> currentUserLanguage, int currentUser, int languageIndex) {
            if (currentUserLanguage.contains(languageIndex)) {
                return 0;
            }
            for (Set<Integer> integers : friendListLanguage) {
                boolean contains = false;
                for (Integer integer : integers) {
                    if (currentUserLanguage.contains(integer)) {
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    currentUserLanguage.add(languageIndex);
                    return 1;
                }
            }
            return 0;
        }
    }


    class Solution1 {
        public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
            HashSet<Integer> set = new HashSet<>();
            for (int[] friends : friendships) {
                int[] langs = new int[n + 1];
                for (int lang : languages[friends[0] - 1]) {
                    langs[lang]++;
                }

                boolean found = false;
                for (int l : languages[friends[1] - 1]) {
                    if (langs[l] > 0) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    set.add(friends[0] - 1);
                    set.add(friends[1] - 1);
                }
            }
            int counts[] = new int[n + 1];
            int max = 0;
            for (int user : set) {
                for (int lang : languages[user]) {
                    counts[lang]++;
                    max = Math.max(max, counts[lang]);
                }
            }
            return set.size() - max;
        }
    }
}
