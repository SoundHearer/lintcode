package jiuzhang.chapter3;

/**
 * 在一个字符串中查询另外一个字符串第一次出现的位置
 * @Author: SelectBook
 * @Date: 2022/8/11 0:55
 */
public class StrStr {

    /**
     * 我们可以让字符串target 与字符串 source 的所有长度为 target.length() 的子串均匹配一次。
     * 为了减少不必要的匹配，我们每次匹配失败即立刻停止当前子串的匹配，
     * 对下一个子串继续匹配。如果当前子串匹配成功，我们返回当前子串的开始位置即可。
     * 如果所有子串都匹配失败，则返回 -1−1
     *
     * @param source
     * @param target
     * @return
     */
    public int strStr(String source, String target) {
        if (target == null || target.equals("")) {
            return 0;
        }
        
        for (int i = 0; i < source.length() - target.length(); i++) {
            boolean notEqual = false;
            for (int j = 0; i < target.length(); j++) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    notEqual = true;
                    break;
                }
            }
            if (!notEqual) {
                return i;
            }
        }
        return -1;
    }
}
