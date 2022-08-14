package jiuzhang.chapter4;

/**
 * @Author: SelectBook
 * @Date: 2022/8/15 1:40
 */
public class ReRange {

    public void rerange(int[] A) {
        int pos = 0, neg = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                pos++;
            } else {
                neg++;
            }
        }

        partition(A, pos > neg);
        interleave(A, pos == neg);
    }

    private void partition(int[] A, boolean startPositive) {
        int flag = startPositive ? 1 : -1;
        int left = 0, right = A.length - 1;
        while (left <= right) {
            while (left <= right && A[left] * flag > 0) {
                left++;
            }
            while (left <= right && A[right] * flag < 0) {
                right--;
            }

            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;

                left++;
                right--;
            }
        }
    }

    private void interleave(int[] A, boolean has_same_length) {
        int left = 1, right = A.length - 1;
        if (has_same_length) {
            right = A.length - 2;
        }
        while (left < right) {
            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;

            left += 2;
            right -= 2;
        }
    }
}
