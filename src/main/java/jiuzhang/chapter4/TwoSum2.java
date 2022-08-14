package jiuzhang.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序数组 + 双指针 list + two pointers
 * @Author: SelectBook
 * @Date: 2022/8/11 2:57
 */
public class TwoSum2 {
    
    // list装升序数据
    public List<Integer> nums;
    public TwoSum2() {
        nums = new ArrayList<>();
    }
    
    public void add(int number) {
        nums.add(number);
        int index = nums.size() - 1;
        // 按照插入排序的方法，加入新数字，保持nums升序
        // 每次将数据最后一个元素作为插入元素，与它前面有序的数组元素进行比较
        // 如果没有在正确的位置，交换两元素，进行下一轮比较
        while (index > 0 && nums.get(index - 1) > nums.get(index)) {
            swap(nums, index);
            index--;
        }
    }
    
    public void swap(List<Integer> nums, int index) {
        int temp = nums.get(index);
        nums.set(index, nums.get(index - 1));
        nums.set(index - 1, temp);
    }

    /**
     * 经典twosum, 在排序数组中用双指针寻找一对和为target的数字
     * @param targetValue
     * @return
     */
    public boolean find(int targetValue) {
        // 左右两颗相向指针
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int twoSum = nums.get(left) + nums.get(right);
            // twoSum小于target，左指针向中间移动，寻找更大twoSum
            if (twoSum< targetValue) {
                left++;
            }
            // twoSum大于target，右指针向中间移动，寻找更小twoSum
            else if (twoSum > targetValue) {
                right--;
            }
            // 找到，则返回true
            else {
                return true;
            }
        }
        // 找不到，返回false
        return false;
    }
    
}
