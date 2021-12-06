package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * K次取反后最大化的数组和
 */
public class LargestSumAfterKNegations {


    /**
     * 对数组进行升序排序，首先依次遍历每一个负数（将负数修改为正数），再遍历所有的数（将 00 或最小的正数进行修改）。
     * 数组元素的范围为 [-100, 100][−100,100]，因此我们可以使用计数数组（桶）或者哈希表，
     * 直接统计每个元素出现的次数，再升序遍历元素的范围，这样就省去了排序需要的时间。
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations1(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = Arrays.stream(nums).sum();
        for (int i = -100; i < 0; ++i) {
            if (freq.containsKey(i)) {
                int ops = Math.min(k, freq.get(i));
                ans += (-i) * ops * 2;
                freq.put(i, freq.get(i) - ops);
                freq.put(-i, freq.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }
        if (k > 0 && k % 2 == 1 && !freq.containsKey(0)) {
            for (int i = 1; i <= 100; ++i) {
                if (freq.containsKey(i)) {
                    ans -= i * 2;
                    break;
                }
            }
        }
        return ans;
    }

    public int largestSumAfterKNegations2(int[] nums, int k) {
        // 排序，把可能有的负数排到前面
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 贪心：如果是负数，而k还有盈余，就把负数反过来
            if (nums[i] < 0 && k > 0) {
                nums[i] = -1 * nums[i];
                k--;
            }
            sum += nums[i];
        }
        Arrays.sort(nums);
        // 如果k没剩，那说明能转的负数都转正了，已经是最大和，返回sum
        // 如果k有剩，说明负数已经全部转正，所以如果k还剩偶数个就自己抵消掉，不用删减，如果k还剩奇数个就减掉2倍最小正数。
        return sum - (k % 2 == 0 ? 0 : 2 * nums[0]);
    }

    /**
     * @param nums
     * @param k
     * @return
     */
    public static int largestSumAfterKNegations(int[] nums, int k) {
        // 数组内负数个数
        int countNegative = 0;
        // 数组是否有0
        int countZero = 0;
        // 数组和
        int sum = -1;
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                countNegative++;
            }
            if (nums[i] == 0) {
                countZero++;
            }
        }
        if (k <= countNegative) {
            // 当给定的K小于等于数组中负数的个数时，从小到大依次修改每一个负数即可
            negate(nums, k);
        } else {
            // 当K值大于数组中的负数个数
            // 如果数组中存在0, 那么我们可以对它进行多次修改，直到把剩余的修改次数用完
            if (countZero > 0) {
               negate(nums, countNegative);
            } else {
                if ((k - countNegative) % 2 == 0) {
                    // 如果数组中不存在0并且剩余的修改次数是偶数，由于对同一个数修改两次等价于不进行修改，
                    // 因此我们可以在不减小数组的和的前提下，把修改次数用完
                    negate(nums, countNegative);
                } else {
                    // 如果数组中不存在0, 并且剩余的修改次数是奇数，那么我们必然需要使用单独的一次修改将一个最小的正数变为负数
                    // 需要注意的是，在之前将负数修改为正数的过程中，可能出现了（相较于原始数组中最小的正数）更小的正数
                    negate(nums, countNegative);
                    // 重新排序，
                    // 此时数据中已经没有负数，因此最小的正数就是数组中最小的数
                    sort(nums);
                    nums[0] = -nums[0];
                }
            }
        }
        return sum(nums);
    }

    /**
     * 数组求和
     *
     * @param a
     * @return
     */
    public static int sum(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    /**
     * 取反
     * @param a
     * @param k
     */
    public static void negate(int[] a, int k) {
        for (int i = 0; i < k; i++) {
            a[i] = -a[i];
        }
    }

    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            // 交换
            if (a[i] > a[min]) {
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 3};
        int sum = largestSumAfterKNegations(a, 1);
        System.out.println(sum);
        System.out.print(Arrays.toString(a));
    }
}
