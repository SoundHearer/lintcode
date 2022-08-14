package jiuzhang.chapter4;

/**
 * 是否可以在去掉一个字符的情况下是一个回文串
 * abca 可以，去掉b或者c
 * abc 无法做到
 */

class Pair {
    int left, right;
    public Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

public class ValidPalindrome {
    
    public boolean validPalindrome(String s) {
        if (s == null) {
            return false;
        }
        
        Pair pair = findDifference(s, 0, s.length() - 1);
        if (pair.left >= pair.right) {
            return true;
        }
        
        return isPalindrome(s, pair.left + 1, pair.right) || isPalindrome(s, pair.left, pair.right - 1);
    }
    
    private Pair findDifference(String s, int left, int right) {
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        return new Pair(left, right);
    }
    
    private boolean isPalindrome(String s, int left, int right) {
        Pair pair = findDifference(s, left, right);
        return pair.left >= pair.right;
    }

    /**
     * 删除N个字符的通用模板
     * 双指针算法。从两头走到中间，发现第一对不一样的字符之后，要么删左边的，要么删右边的。
     * @param s
     * @return
     */
    public boolean validPalindrome1(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left++;
            right--;
        }

        if (left >= right) {
            return true;
        }

        return isSubPalindrome(s, left + 1, right) || isSubPalindrome(s, left, right - 1);
    }

    private boolean isSubPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
    
}
