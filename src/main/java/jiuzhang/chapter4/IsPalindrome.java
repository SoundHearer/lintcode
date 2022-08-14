package jiuzhang.chapter4;

/**
 * 判断一个字符串忽略大小写和非法字符之后是否是一个回文串
 *  race a car 不是回文
 *  A man, a plan, a canal: Panama 是回文
 *  
 * @Author: SelectBook
 * @Date: 2022/8/11 1:44
 */
public class IsPalindrome {
    
    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !isValid(s.charAt(left))) {
                left++;
            }
            while (left < right && !isValid(s.charAt(right))) {
                right--;
            }
            if (left < right && !isEqual(s.charAt(left), s.charAt(right))) {
                System.out.println(s.charAt(left));
                System.out.println(s.charAt(right));
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    private static boolean isValid(char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }
    
    private static boolean isEqual(char a, char b) {
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }
    
    public static boolean isPalindrome0(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }
    
    public static void main(String[] args) {
//        String s = "A man, a plan, a canal: Panama";
        String s = "amanaplanacanalpanama";
        System.out.println(isPalindrome0(s));
    }
}
