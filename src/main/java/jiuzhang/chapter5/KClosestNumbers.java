package jiuzhang.chapter5;

/**
 * @Author: SelectBook
 * @Date: 2022/8/15 23:03
 */
public class KClosestNumbers {
    
    public int[] kClosestNumbers(int[] A, int target, int k) {
        int left = findLowerClosest(A, target);
        int right = left + 1;
        
        int[] results = new int[k];
        for (int i = 0; i < k; i++) {
            // 如果左边更接近，选左边
            if (isLeftCloser(A, target, left, right)) {
                results[i] = A[left];
                left--;
            } else {
                results[i] = A[right];
                right--;
            }
        }
        return results;
    }
    
    private boolean isLeftCloser(int[] A, int target, int left, int right) {
        // 如果左边已经耗尽，返回false
        if (left < 0) {
            return false;
        }
        
        // 如果右边已经耗尽，返回true
        if (right >= A.length) {
            return true;
        }
        
        // 为什么有等号？如果左右距离相等，选左边
        return target - A[left] <= A[right] - target;
    }
    
    // 找到比target小的最右一个数
    private int findLowerClosest(int[] A, int target) {
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // 如果mid < target,答案在右边，丢掉左边
            if (A[mid] < target) {
                start = mid;
            }
            // 如果mid >= target，答案在左边，丢掉右边
            else {
                end = mid;
            }
        }
        
        // 因为需要找最右数，所以这里需要先判断end
        if (A[end] < target) {
            return end;
        }
        
        // 如果end不行，再判断left
        if (A[start] < target) {
            return start;
        }
        // 找不到，说明所有数据都>=target
        return -1;
    }
}
