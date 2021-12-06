package string;

/**
 * 子字符串匹配
 */
public class SubString {

    public int substring(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i < N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == M) {
                // 找到匹配
                return i;
            }
        }
        // 未找到匹配
        return N;
    }
}
