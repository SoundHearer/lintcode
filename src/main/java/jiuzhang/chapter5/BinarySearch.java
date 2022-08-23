package jiuzhang.chapter5;

/**
 * @Author: SelectBook
 * @Date: 2022/8/15 21:48
 */
public class BinarySearch {
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        // 用start + 1 < end 而不是start < end 的目的是为了避免死循环
        // 在first position of target的情况下不会出现死循环
        // 但是在last position of target的情况下会出现死循环
        // 样例： nums[1, 1] target == 1
        // 为了统一模板，我们就都采用start + 1 < end, 就保证不会出现死循环
        while (start + 1 < end) {
            // python没有overflow的问题，直接/2就可以
            // Java和C++最好写成mid = start + (end - start) / 2
            // >, =, < 的逻辑先分开写，然后在看看=的情况是否能合并到其他分支里
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                // 写作 start = mid + 1 也是正确的
                // 只是可以偷懒不写，因为不写没问题，不会影响时间复杂度
                // 不写的好处是，万一你不小心写成了 mid - 1你就错了
                start = mid;
            } else if (nums[mid] == target) {
                end = mid;
            } else {
                // 写作end = mid - 1也是正确的
                // 只是可以偷懒不写，因为不写也没问题，不会影响复杂度，不写的好吃是
                // 万一不小心写成了mid + 1你就错了
                end = mid;
            }
        }
        // 因为上面的循环退出条件是start + 1 < end
        // 因此这里循环结束的时候, start 和 end的关系是相邻关系
        // 因此需要再单独判断 start 和 end 这两个数谁是我们想要的答案
        // 如果是找 first position of target就先看start,否则就先看end
        if (nums[start] == target) {
            return start;
        }
        
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}
