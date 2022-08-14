package jiuzhang.chapter4;

/**
 * 
 * 给出一个整数数组 nums 和一个整数 k。划分数组（即移动数组 nums 中的元素），使得：
 * 所有小于k的元素移到左边，所有大于等于k的元素移到右边
 * 返回数组划分的位置，即数组中第一个位置 i，满足 nums[i] 大于等于 k。
 * @Author: SelectBook
 * @Date: 2022/8/15 0:49
 */
public class PartitionArray {
    
    public int partition(int[] nums, int k) {
        // 特殊情况处理
        if (nums == null) {
            return 0;
        }
        
        // 两端的相向指针
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // 左指针寻找一个不属于左边的数字
            while (left <= right && nums[left] < k) {
                left++;
            }
            // 右指针寻找一个不属于右边的数字
            while (left <= right && nums[right] >= k) {
                right--;
            }
            
            if (left <= right) {
                // 交换左右指针数字，双方都到了正确的一端
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                
                left++;
                right--;
            }
        }
        // 左指针位置即为右边partition七点
        return left; 
    }
}
