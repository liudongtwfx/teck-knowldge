package algorithme.string;

public class SplitTwoStringsToMakePalindrome1616 {
    public static void main(String[] args) {
        new SplitTwoStringsToMakePalindrome1616().bridge();
    }

    private void bridge() {
        Solution solution = new Solution();
        String a = "aejbaalflrmkswrydwdkdwdyrwskmrlfqizjezd";
        String b = "uvebspqckawkhbrtlqwblfwzfptanhiglaabjea";

        System.out.println(solution.checkPalindromeFormation(a, b));
    }


    class Solution {
        public boolean checkPalindromeFormation(String a, String b) {
            return innerCheck(a, b) || innerCheck(b, a);
        }

        private boolean innerCheck(String left, String right) {
            int end = left.length() - 1;
            int start = 0;
            while (start < end) {
                if (left.charAt(start) != right.charAt(end)) {
                    break;
                }
                start++;
                end--;
            }
            return isPalindrome(left.substring(start, end + 1)) || isPalindrome(right.substring(start, end + 1));
        }

        private boolean isPalindrome(String s) {
            System.out.println(s);
            int start = 0;
            int end = s.length() - 1;
            while (start < end) {
                if (s.charAt(start++) != s.charAt(end--)) {
                    return false;
                }
            }
            return true;
        }
    }
}
