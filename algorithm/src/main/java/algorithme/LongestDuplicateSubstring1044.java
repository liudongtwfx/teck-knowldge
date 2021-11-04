package algorithme;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


public class LongestDuplicateSubstring1044 {
    public static void main(String[] args) {
        new LongestDuplicateSubstring1044().bridge();
    }

    private void bridge() {
        String s = "\"okmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajvielokmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajviel\"";
        System.out.println(s.length());
        System.out.println(new Solution().longestExist(s, 800));
    }

    class Solution {
        private static final int MOD = 2147483629;
        private final AtomicInteger count = new AtomicInteger();
        private String ans = "";

        public String longestDupSubstring(String s) {
            return binarySearch(s, 0, s.length());
        }

        private String binarySearch(String s, int lengthStart, int lengthEnd) {
            if (lengthEnd - lengthStart <= 3) {
                for (int i = Math.min(lengthEnd, s.length() - 1); i >= lengthStart; i--) {
                    String s1 = longestExist(s, i);
                    if (s1 != null) {
                        return s1;
                    }
                }
                return "";
            }
            int mid = (lengthStart + lengthEnd) / 2;
            System.out.println(mid + " " + lengthStart + " " + lengthEnd);
            if (longestExist(s, mid) != null) {
                return binarySearch(s, mid + 1, lengthEnd);
            }
            return binarySearch(s, lengthStart, mid);
        }


        private String longestExist(String s, int length) {
            // System.out.println(length);
            Map<Long, Set<String>> hashCache = new HashMap<>();
            long mode = 1;
            long hash = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < length; i++) {
                hash = ((hash * 31) + (chars[i] - 'a'));
                if (i != length - 1) {
                    mode = (mode * 31);
                }
            }
            Set<String> strings = new HashSet<>();
            strings.add(s.substring(0, length));
            hashCache.put(hash, strings);
            for (int i = 1; i + length <= s.length(); i++) {
                long value = mode * (chars[i - 1] - 'a');
                hash -= value;
                hash = (hash * 31 + chars[i + length - 1] - 'a');
                Set<String> original = hashCache.getOrDefault(hash, new HashSet<>());
                String s1 = s.substring(i, i + length);
                if (!original.add(s1)) {
                    return s1;
                }
                hashCache.put(hash, original);
            }
            return null;
        }
    }
}
