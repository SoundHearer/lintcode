package string;

/**
 * 截断句子
 * 除了最后一个单词，每个单词后面都跟随一个空格
 * 因此我们可以通过统计空格与句子结尾的数目来统计单词数 count。
 * 当 count=k 时，将当前的下标记录到 end，返回句子 s 在 end 处截断的句子
 *
 */
public class TruncateSentence {

    public String truncateSentence(String s, int k) {
        int n = s.length();
        int end = 0, count = 0;
        for (int i = 1; i <= n; i++) {
            if (i == n || s.charAt(i) == ' ') {
                count++;
                if (count == k) {
                    end = i;
                    break;
                }
            }
        }
        return s.substring(0, end);
    }
}
