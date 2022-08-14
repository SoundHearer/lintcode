package jiuzhang.chapter4;

import java.util.Arrays;

/**
 * 
 * 给定一个整数数组，在该数组中，寻找三个数，分别代表三角形三条边的长度，问，可以寻找到多少组这样的三个数来组成三角形？
 * @Author: SelectBook
 * @Date: 2022/8/13 18:13
 */
public class TriangleCount {

    /**
     * 固定最大边（初始值下标为2，保证之前至少有两个边），在最大边之前寻找两条较小边
     * @param S
     * @return
     */
    public static int triangleCount(int[] S) {
        // 特殊情况处理
        if (S == null || S.length < 3) {
            return 0;
        }
        // 经典two sum需要在有序数据上进行
        Arrays.sort(S);
        int cnt = 0;
        // 遍历最大边，在最大边的左边寻找两个小边
        for (int i = 2; i < S.length; i++) {
            cnt += getTriangleCount(S, i);
        }
        return cnt;    
    }
    
    private static int getTriangleCount(int[] arr, int targetIndex) {
        int cnt = 0;
        // 寻找范围为[0, targetIndex - 1]
        int left = 0;
        int right = targetIndex - 1;
        int targetNum = arr[targetIndex];
        int sum = 0;
        while (left < right) {
            sum = arr[left] + arr[right];
            // sum大于target，可以组成三角形
            if (sum > targetNum) {
                // 一次求出多个可行解的个数
                cnt += right - left;
                // 已经计入右指针所有可能的组合，右指针向中间移动
                right--;
            }
            // sum小于target,左指针向右移动，寻找sum大于target的数
            else {
                left++;
            }
        }
        return cnt;
    }
    
    public static void main(String[] args) {
        int[] nums = {4, 3, 6, 7};
        System.out.print(triangleCount(nums));
    }
    
}
