package util;

public class MSD {
    // 基数
    private static int R = 256;
    // 小数组的切换阈值
    private static final int M = 15;
    // 数组分类的辅助数组
    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

}
