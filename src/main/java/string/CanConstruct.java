package string;

/**
 * 判断 ransomNote 能不能由 magazines 里面的字符构成。
 * 简单计数模拟
 *
 */
public class CanConstruct {

    /**
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        // 先使用大小为字符集大小的数组（充当哈希表）对 magazinemagazine 进行词频统计
        // 然后在遍历 ransomNote 时对计数数组进行抵消操作
        // 若处理过程中出现词频数量为负数，则说明 magazinemagazine 不能凑出 ransomNoteransomNote
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "ab";
        System.out.println(canConstruct(ransomNote, magazine));
    }
}
