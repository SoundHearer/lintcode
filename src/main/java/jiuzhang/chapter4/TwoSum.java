package jiuzhang.chapter4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: SelectBook
 * @Date: 2022/8/11 2:48
 */
public class TwoSum {

    /**
     * 暴力枚举
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum0(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 排序 + 双指针
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum01(int[] nums, int target) {
        if (nums == null) {
            return new int[0];
        }
        Arrays.sort(nums);

        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                left++;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                return new int[]{left, right};
            }
        }
        return new int[0];
    }

    /**
     * 注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。
     * 因此，我们需要一种更优秀的方法，能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     *
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N)O(N) 降低到 O(1)。
     * 我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，
     * 然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum02(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
    
    
    /**
     * 用一个hashmap来记录，key记录target-numbers[i]的值，value记录numbers[i]的i的值，如果碰到一个
     * numbers[j]在hashmap中存在，那么说明前面的某个numbers[i]和numbers[j]的和为target，i和j即为答案
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (map.get(numbers[i]) != null) {
                int[] result = {map.get(numbers[i]), i};
                return result;
            }
            map.put(target - numbers[i], i);
        }

        int[] result = {};
        return result;
    }

    class Pair implements Comparable<Pair> {
        int number, index;

        public Pair(int number, int index) {
            this.number = number;
            this.index = index;
        }

        @Override
        public int compareTo(Pair other) {
            return number - other.number;
        }
    }
    
    /**
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1, index2] (index1 < index2)
     */
    public int[] twoSum2(int[] numbers, int target) {
        int[] result = {-1, -1};

        if (numbers == null) {
            return result;
        }

        Pair[] pairs = getSortedPairs(numbers);

        int left = 0, right = pairs.length - 1;
        while (left < right) {
            if (pairs[left].number + pairs[right].number < target) {
                left++;
            } else if (pairs[left].number + pairs[right].number > target) {
                right--;
            } else {
                result[0] = Math.min(pairs[left].index, pairs[right].index);
                result[1] = Math.max(pairs[left].index, pairs[right].index);
                return result;
            }
        }

        return result;
    }

    private Pair[] getSortedPairs(int[] numbers) {
        Pair[] pairs = new Pair[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            pairs[i] = new Pair(numbers[i], i);
        }
        Arrays.sort(pairs);

        return pairs;
    }
    
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum02(nums, target)));
    }
}
