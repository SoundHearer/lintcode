package jiuzhang.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组。
 * 在三元组(a, b, c)，要求a <= b <= c。
 * 结果不能包含重复的三元组。
 * 
 * @Author: SelectBook
 * @Date: 2022/8/14 20:10
 */
public class ThreeSum {

    /**
     * 暴力可行解：三层for循环，找到所有满足条件的三元组，并去重。时间复杂度O(N^3)，空间复杂度O(1)
     * 优化解：如果选定一个点A，那么另外两个点的目标和为-A，转化为two sum问题。
     * 选定三元组中的最小值，中间值，还是最大值？
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        
        // 特殊情况处理
        if (nums == null || nums.length < 3) {
            return results;
        }
        
        // 原数据无序，经典two sum需要在有序列中进行
        Arrays.sort(nums);
        
        // 遍历三元组中的最小数
        for (int i = 0; i < nums.length - 1; i++) {
            // 如果当前元素和左边元素一样，跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 好的代码不需要太多注释
            int left = i + 1, right = nums.length - 1;
            int target = -nums[i];
            // 用经典two sum寻找所有和为target的不重复元祖
            twoSum(nums, left, right, target, results);
        }
        return results;
    }
    
    public void twoSum(int[] nums, int left, int right, int target, List<List<Integer>> results) {
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                generateTriplet(nums, results, left, right, target);
                left++;
                right--;
                // 如果左指针当前数字跟左边数字相同，左指针向中间移动，跳过重复
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                // 如果右指针当前数字跟右边数字相同，右指针向中间移动，跳过重复
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            }
            // 当前sum小于target,右移左指针，去寻找更大的sum
            else if (sum < target) {
                left++;
            }
            // 当前sum大于target,左移右指针，去寻找更小的sum
            else {
                right--;
            }
        }
    }
    
    private void generateTriplet(int[] nums, List<List<Integer>> results, int left, int right, int target) {
        ArrayList<Integer> triplet = new ArrayList<>();
        triplet.add(-target);
        triplet.add(nums[left]);
        triplet.add(nums[right]);
        results.add(triplet);
    }
    
}
