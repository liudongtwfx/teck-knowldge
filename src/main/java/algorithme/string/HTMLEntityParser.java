package algorithme.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 1410. HTML Entity Parser
 * https://leetcode.com/problems/html-entity-parser/
 */
public class HTMLEntityParser {
    public static void main(String[] args) {
        System.out.println(new Solution1().entityParser("&amp;gt;"));
    }

    static class Solution {
        static Map<String, String> replaceConfig = new HashMap<>();

        static {
            replaceConfig.put("&quot;", "\"");
            replaceConfig.put("&apos;", "'");
            replaceConfig.put("&amp;", "&");
            replaceConfig.put("&gt;", ">");
            replaceConfig.put("&lt;", "<");
            replaceConfig.put("&frasl;", "/");
        }

        public String entityParser(String text) {
            StringBuilder result = new StringBuilder();
            int start = 0;
            while (start < text.length()) {
                boolean matched = false;
                for (Map.Entry<String, String> stringStringEntry : replaceConfig.entrySet()) {
                    //if()
                    if (text.startsWith(stringStringEntry.getKey(), start)) {
                        result.append(stringStringEntry.getValue());
                        start += stringStringEntry.getKey().length();
                        matched = true;
                        break;
                    }
                }
                if (!matched) {
                    result.append(text.charAt(start));
                    start++;
                }
            }
            return result.toString();
        }
    }


    static class Solution1 {
        static Map<String, String> replaceConfig = new HashMap<>();

        static {
            replaceConfig.put("&quot;", "\"");
            replaceConfig.put("&apos;", "'");
            replaceConfig.put("&amp;", "&");
            replaceConfig.put("&gt;", ">");
            replaceConfig.put("&lt;", "<");
            replaceConfig.put("&frasl;", "/");
        }

        public String entityParser(String text) {
            String result = "";
            int start = 0;
            while (start < text.length()) {
                int minStart = text.length();
                String key = null;
                for (Map.Entry<String, String> stringStringEntry : replaceConfig.entrySet()) {
                    //if()
                    int index = text.indexOf(stringStringEntry.getKey(), start);
                    if (index != -1 && minStart > index) {
                        key = stringStringEntry.getKey();
                        minStart = index;
                    }
                }
                if (minStart == text.length()) {
                    return result + text.substring(start, minStart);
                }
                result += text.substring(start, minStart) + replaceConfig.get(key);
                start = minStart + key.length();
            }
            return result;
        }
    }
}
