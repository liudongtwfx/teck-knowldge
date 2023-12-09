package spring.beantwo;

import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author liudong
 */
@Component
public class Test {
    public static void main(String[] args) {
        String shortName = ClassUtils.getShortName(Test.class);
        System.out.println(shortName);
        System.out.println(Test.class.getName());
    }

    class Solution {
        public String sortVowels(String s) {
            List<Character> vowelCharacters = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (isVowel(c)) {
                    vowelCharacters.add(c);
                }
            }
            if (vowelCharacters.isEmpty()) {
                return s;
            }
            vowelCharacters.sort(Comparator.naturalOrder());
            StringBuilder sb = new StringBuilder();
            int start = 0;
            for (char c : s.toCharArray()) {
                if (isVowel(c)) {
                    sb.append(vowelCharacters.get(start));
                    start++;
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }

        private boolean isVowel(char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                    || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
        }
    }

    private void a(Object b) {
        if (b instanceof String str) {
            System.out.println(str.isEmpty());
        }
    }
}
