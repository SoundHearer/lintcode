package jiuzhang.chapter2;

/**
 * @Author: SelectBook
 * @Date: 2022/8/7 20:39
 */
public class LongestPalindrome1 {
    
    public static int longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        // 边界判断
        if (s.length() == 1) {
            return 1;
        }

        int longestPalindrome = 0;
        // 遍历所有的子字符串
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j < s.length(); j++) {
                System.out.println(i + " " + j);
                String s1 = s.substring(i, j + 1);
                if (isPalindrome(s1) && longestPalindrome < s1.length()) {
                    longestPalindrome = s1.length();
                }
            }
        }
        return longestPalindrome;
    }

    // 判断回文串
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    public static void kinds(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                System.out.println(a[i] + " " + a[j]);
                sum++;
                System.out.println(sum);
            }
        }
    }

    public static void kinds1(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.println(a[i] + " " + a[j]);
                sum++;
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        String s = "abccccdd";
        int[] a = {1,2,3,4,5,6,7,8};
        System.out.println(longestPalindrome(s));
//        kinds1(a);
    }
}
