package jiuzhang.chapter2;

/**
 * 最长回文子串
 * 
 * @Author: SelectBook
 * @Date: 2022/8/7 14:18
 */
public class LongestPalindromeSubString {

    /**
     * 自己的写法
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        // 边界判断
        if (s == null) {
            return null;
        }
        
        if (s.length() == 1) {
            return s;
        }
        
        String longestPalindrome = "";
        // 遍历所有的子字符串
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                String s1 = s.substring(i, j + 1);
                if (isPalindrome(s1) && longestPalindrome.length() < s1.length()) {
                    longestPalindrome = s1;
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

    /**
     * 方法一
     *   解题思路
     *   最暴力的想法是，枚举子串的头尾，判断该子串是否是回文串。
     *   
     *   复杂度分析
     *   设字符串的长度为 N。
     *  
     *   时间复杂度
     *   枚举首尾的复杂度为 O(N^2)。
     *   check 一个字符串是否是回文，复杂度为 O(N)。
     *   总时间复杂度为 O(N^3)。
     *   
     *   空间复杂度
     *  * 如果用substring函数取出子串并判断的话，空间复杂度为O(n)。
     *  * 如果直接在原数组上对子串进行check的话，空间复杂度为o(1)。
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        int len = s.length();
        int maxLen = 0;
        String result = null;
        for (int start = 0; start < len; start++) {
            for (int end = start; end < len; end++) {
                // 小优化
                if (end - start + 1 <= maxLen) {
                    continue;
                }

                // 如果是回文串的话，更新答案
                if (isPalindrome(s, start, end)) {
                    maxLen = end - start + 1;
                    result = s.substring(start, end + 1);
                }
            }
        }
        return result;
    }
    
    private boolean isPalindrome(String s, int start, int end) {
        for (int i = 0; start + i < end - i; i++) {
            if (s.charAt(start + i) != s.charAt(end - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二 中心枚举
     * 解题思路
     * 直接枚举子串首尾位置再判断是否回文，时间复杂度为O(N^3)，
     * 换个思路，枚举回文串的对称中心位置，向两侧扫描检测最长回文长度时间复杂度为O(N^2)
     * 由回文串正序和反序的性质相同，可以得出一个性质，如果一个字符串，其中心不是回文串，那么它一定不是个回文串。所以我们每次从中心开始，向两边延展首尾，判断是否是回文串。
     *
     * 代码思路
     * 枚举中心 center，需要两个指针 start， end。
     * 如果 s[start] == s[end]，则 start--，end++，更新答案
     * 重复上一步，直到不相等就停止。
     * 注意：奇数和偶数长度的回文串是不同的，奇数中心是单独的一个字符，偶数的是相邻的两个字符。
     * 复杂度分析
     * 设字符串的长度为 N。
     *
     * 时间复杂度
     * 枚举回文中心，复杂度 O(n)。
     * 向两边延展并 check，复杂度 O(n)。
     * 总时，时间复杂度为 O(n^2)。
     *
     * 空间复杂度
     * 不需要额外变量，空间复杂度为 O(1)。
     *
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        int maxLen = 0;
        String result = null;
        for (int center = 0; center < len; center++) {
            // 奇数长度情况
            for (int start = center, end = center;
                 valid(start, end, len); start--, end++) {
                if (s.charAt(start) != s.charAt(end)) {
                    break;
                }
                int newLen = end - start + 1;
                if (newLen > maxLen) {
                    maxLen = newLen;
                    result = s.substring(start, end + 1);
                }
            }
            // 偶数长度情况
            for (int start = center, end = center + 1;
                 valid(start, end, len); start--, end++) {
                if (s.charAt(start) != s.charAt(end)) {
                    break;
                }
                int newLen = end - start + 1;
                if (newLen > maxLen) {
                    maxLen = newLen;
                    result = s.substring(start, end + 1);
                }
            }
        }
        return result;
    }
    private boolean valid(int start, int end, int len) {
        return start > -1 && end < len;
    }

    /**
     * 方法三
     * 解题思路
     * 由回文串正序和反序的性质相同，可以得出一个性质，如果一个字符串，其中心不是回文串，那么它一定不是个回文串。如果去掉头和尾，它依然还是一个回文串。在头和尾加上同一个字符也是一个回文串。
     *
     * 由此可以得出判断一个区间是否是回文串，可以由更小的区间得到，并且不受包含这个区间的大区间影响，所以满足无后效性且是最有子结构，可以用动态规划求解。
     *
     * 代码思路
     * 动态规划
     *
     * 设dp(l, r)，代表区间[l, r]是否是回文串。
     *
     * 如果s[l] == s[r]，并且s[l + 1 ~ r - 1]是回文串的话，s[l ~ r]就是回文串。
     *
     * 复杂度分析
     * 设字符串长度为 N。
     *
     * 时间复杂度
     * 枚举端点，O(1)时间转移，时间复杂度为O(n^2)。
     * 空间复杂度
     * 需要额外O(n^2)空间记录dp值。
     */
    public String longestPalindrome3(String s) {
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len][len];
        int maxLen = 1;
        int start = 0;

        // 将长度为 1 的 dp 值设为真
        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i + 1 < len; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                isPalindrome[i][i + 1] = true;
                maxLen = 2;
                start = i;
            }
        }
        for (int left = len - 1; left >= 0; left--) {
            for (int right = left + 2; right < len; right++) {
                if (isPalindrome[left + 1][right - 1]
                        && s.charAt(left) == s.charAt(right)) {

                    isPalindrome[left][right] = true;
                    // 更新答案
                    if (right - left + 1 > maxLen) {
                        maxLen = right - left + 1;
                        start = left;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
    
    public static void main(String[] args) {
        String s = "abcdzdcab";
        System.out.println(longestPalindrome(s));
    }
}
