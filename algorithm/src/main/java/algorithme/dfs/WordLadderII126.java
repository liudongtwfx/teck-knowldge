package algorithme.dfs;

import com.google.gson.Gson;

import java.util.*;

public class WordLadderII126 {
    public static void main(String[] args) {
        new WordLadderII126().bridge();
    }

    private void bridge() {
        String beginWord = "aaaaa";
        String endWord = "ggggg";
        List<String> wordList = new ArrayList<>(Arrays.asList("aaaaa", "caaaa", "cbaaa", "daaaa", "dbaaa", "eaaaa", "ebaaa", "faaaa", "fbaaa", "gaaaa", "gbaaa", "haaaa", "hbaaa", "iaaaa", "ibaaa", "jaaaa", "jbaaa", "kaaaa", "kbaaa", "laaaa", "lbaaa", "maaaa", "mbaaa", "naaaa", "nbaaa", "oaaaa", "obaaa", "paaaa", "pbaaa", "bbaaa", "bbcaa", "bbcba", "bbdaa", "bbdba", "bbeaa", "bbeba", "bbfaa", "bbfba", "bbgaa", "bbgba", "bbhaa", "bbhba", "bbiaa", "bbiba", "bbjaa", "bbjba", "bbkaa", "bbkba", "bblaa", "bblba", "bbmaa", "bbmba", "bbnaa", "bbnba", "bboaa", "bboba", "bbpaa", "bbpba", "bbbba", "abbba", "acbba", "dbbba", "dcbba", "ebbba", "ecbba", "fbbba", "fcbba", "gbbba", "gcbba", "hbbba", "hcbba", "ibbba", "icbba", "jbbba", "jcbba", "kbbba", "kcbba", "lbbba", "lcbba", "mbbba", "mcbba", "nbbba", "ncbba", "obbba", "ocbba", "pbbba", "pcbba", "ccbba", "ccaba", "ccaca", "ccdba", "ccdca", "cceba", "cceca", "ccfba", "ccfca", "ccgba", "ccgca", "cchba", "cchca", "cciba", "ccica", "ccjba", "ccjca", "cckba", "cckca", "cclba", "cclca", "ccmba", "ccmca", "ccnba", "ccnca", "ccoba", "ccoca", "ccpba", "ccpca", "cccca", "accca", "adcca", "bccca", "bdcca", "eccca", "edcca", "fccca", "fdcca", "gccca", "gdcca", "hccca", "hdcca", "iccca", "idcca", "jccca", "jdcca", "kccca", "kdcca", "lccca", "ldcca", "mccca", "mdcca", "nccca", "ndcca", "occca", "odcca", "pccca", "pdcca", "ddcca", "ddaca", "ddada", "ddbca", "ddbda", "ddeca", "ddeda", "ddfca", "ddfda", "ddgca", "ddgda", "ddhca", "ddhda", "ddica", "ddida", "ddjca", "ddjda", "ddkca", "ddkda", "ddlca", "ddlda", "ddmca", "ddmda", "ddnca", "ddnda", "ddoca", "ddoda", "ddpca", "ddpda", "dddda", "addda", "aedda", "bddda", "bedda", "cddda", "cedda", "fddda", "fedda", "gddda", "gedda", "hddda", "hedda", "iddda", "iedda", "jddda", "jedda", "kddda", "kedda", "lddda", "ledda", "mddda", "medda", "nddda", "nedda", "oddda", "oedda", "pddda", "pedda", "eedda", "eeada", "eeaea", "eebda", "eebea", "eecda", "eecea", "eefda", "eefea", "eegda", "eegea", "eehda", "eehea", "eeida", "eeiea", "eejda", "eejea", "eekda", "eekea", "eelda", "eelea", "eemda", "eemea", "eenda", "eenea", "eeoda", "eeoea", "eepda", "eepea", "eeeea", "ggggg", "agggg", "ahggg", "bgggg", "bhggg", "cgggg", "chggg", "dgggg", "dhggg", "egggg", "ehggg", "fgggg", "fhggg", "igggg", "ihggg", "jgggg", "jhggg", "kgggg", "khggg", "lgggg", "lhggg", "mgggg", "mhggg", "ngggg", "nhggg", "ogggg", "ohggg", "pgggg", "phggg", "hhggg", "hhagg", "hhahg", "hhbgg", "hhbhg", "hhcgg", "hhchg", "hhdgg", "hhdhg", "hhegg", "hhehg", "hhfgg", "hhfhg", "hhigg", "hhihg", "hhjgg", "hhjhg", "hhkgg", "hhkhg", "hhlgg", "hhlhg", "hhmgg", "hhmhg", "hhngg", "hhnhg", "hhogg", "hhohg", "hhpgg", "hhphg", "hhhhg", "ahhhg", "aihhg", "bhhhg", "bihhg", "chhhg", "cihhg", "dhhhg", "dihhg", "ehhhg", "eihhg", "fhhhg", "fihhg", "ghhhg", "gihhg", "jhhhg", "jihhg", "khhhg", "kihhg", "lhhhg", "lihhg", "mhhhg", "mihhg", "nhhhg", "nihhg", "ohhhg", "oihhg", "phhhg", "pihhg", "iihhg", "iiahg", "iiaig", "iibhg", "iibig", "iichg", "iicig", "iidhg", "iidig", "iiehg", "iieig", "iifhg", "iifig", "iighg", "iigig", "iijhg", "iijig", "iikhg", "iikig", "iilhg", "iilig", "iimhg", "iimig", "iinhg", "iinig", "iiohg", "iioig", "iiphg", "iipig", "iiiig", "aiiig", "ajiig", "biiig", "bjiig", "ciiig", "cjiig", "diiig", "djiig", "eiiig", "ejiig", "fiiig", "fjiig", "giiig", "gjiig", "hiiig", "hjiig", "kiiig", "kjiig", "liiig", "ljiig", "miiig", "mjiig", "niiig", "njiig", "oiiig", "ojiig", "piiig", "pjiig", "jjiig", "jjaig", "jjajg", "jjbig", "jjbjg", "jjcig", "jjcjg", "jjdig", "jjdjg", "jjeig", "jjejg", "jjfig", "jjfjg", "jjgig", "jjgjg", "jjhig", "jjhjg", "jjkig", "jjkjg", "jjlig", "jjljg", "jjmig", "jjmjg", "jjnig", "jjnjg", "jjoig", "jjojg", "jjpig", "jjpjg", "jjjjg", "ajjjg", "akjjg", "bjjjg", "bkjjg", "cjjjg", "ckjjg", "djjjg", "dkjjg", "ejjjg", "ekjjg", "fjjjg", "fkjjg", "gjjjg", "gkjjg", "hjjjg", "hkjjg", "ijjjg", "ikjjg", "ljjjg", "lkjjg", "mjjjg", "mkjjg", "njjjg", "nkjjg", "ojjjg", "okjjg", "pjjjg", "pkjjg", "kkjjg", "kkajg", "kkakg", "kkbjg", "kkbkg", "kkcjg", "kkckg", "kkdjg", "kkdkg", "kkejg", "kkekg", "kkfjg", "kkfkg", "kkgjg", "kkgkg", "kkhjg", "kkhkg", "kkijg", "kkikg", "kkljg", "kklkg", "kkmjg", "kkmkg", "kknjg", "kknkg", "kkojg", "kkokg", "kkpjg", "kkpkg", "kkkkg", "ggggx", "gggxx", "ggxxx", "gxxxx", "xxxxx", "xxxxy", "xxxyy", "xxyyy", "xyyyy", "yyyyy", "yyyyw", "yyyww", "yywww", "ywwww", "wwwww", "wwvww", "wvvww", "vvvww", "vvvwz", "avvwz", "aavwz", "aaawz", "aaaaz"));
        System.out.println(new Gson().toJson(new Solution().findLadders(beginWord, endWord, wordList)));
    }


    class Solution {
        private List<List<String>> ansList = new ArrayList<>();

        private Map<String, Integer> layerMap = new HashMap<>();
        private int min = 501;

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) {
                return new ArrayList<>();
            }
            if (!wordList.contains(beginWord)) wordList.add(beginWord);
            Map<String, Set<String>> map = adjacentMap(wordList);
            min = bfsMin(map, beginWord, endWord);
            System.out.println(min);
            if (min == 0) return new ArrayList<>();
            dfs(map, beginWord, endWord, new HashSet<>(), new ArrayList<>(), 1);
            return ansList;
        }

        private int bfsMin(Map<String, Set<String>> map, String beginWord, String endWord) {
            List<String> wordLadders = new ArrayList<>();
            wordLadders.add(beginWord);
            layerMap.put(beginWord, 1);
            int current = 1;
            Set<String> visited = new HashSet<>();
            while (!wordLadders.isEmpty()) {
                current++;
                List<String> next = new ArrayList<>();
                for (String wordLadder : wordLadders) {
                    Set<String> ladders = map.getOrDefault(wordLadder, new HashSet<>());
                    for (String ladder : ladders) {
                        if (ladder.equals(endWord)) {
                            layerMap.put(ladder, current);
                            return current;
                        }
                        if (visited.add(ladder)) {
                            next.add(ladder);
                            layerMap.put(ladder, current);
                        }
                    }
                }
                wordLadders = next;
            }
            return 0;
        }

        private void dfs(Map<String, Set<String>> map, String current, String endWord, Set<String> visited, List<String> path, int layer) {
            if (path.size() >= min) {
                return;
            }
            if (current.equals(endWord)) {
                List<String> newPath = new ArrayList<>(path);
                newPath.add(endWord);
                ansList.add(newPath);
                return;
            }
            if (!visited.add(current)) return;
            path.add(current);
            if (map.get(current) != null) {
                for (String s : map.get(current)) {
                    if (!visited.contains(s) && layerMap.getOrDefault(s, -1) == layer + 1) {
                        dfs(map, s, endWord, visited, path, layer + 1);
                    }
                }
            }
            visited.remove(current);
            path.remove(path.size() - 1);
        }

        private Map<String, Set<String>> adjacentMap(List<String> wordList) {
            Map<String, Set<String>> map = new HashMap<>();
            for (int i = 0; i < wordList.size(); i++) {
                String word = wordList.get(i);
                for (int j = i + 1; j < wordList.size(); j++) {
                    if (isAdjacent(word, wordList.get(j))) {
                        putToMap(map, word, wordList.get(j));
                        putToMap(map, wordList.get(j), word);
                    }
                }
            }
            return map;
        }

        private void putToMap(Map<String, Set<String>> map, String left, String right) {
            Set<String> adjacentList = map.getOrDefault(left, new HashSet<>());
            adjacentList.add(right);
            if (adjacentList.size() == 1) map.put(left, adjacentList);
        }

        private boolean isAdjacent(String left, String right) {
            if (left.length() != right.length()) return false;
            int diffCount = 0;
            for (int i = 0; i < left.length(); i++) {
                if (left.charAt(i) != right.charAt(i)) {
                    diffCount++;
                }
                if (diffCount > 1) return false;
            }
            return true;
        }
    }
}
