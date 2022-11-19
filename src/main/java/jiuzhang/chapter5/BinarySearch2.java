package jiuzhang.chapter5;

import java.util.Scanner;

public class BinarySearch2 {
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            String[] nums = sc.nextLine().split(",");
            if(sc.hasNextLine()){
                int target = Integer.parseInt(sc.nextLine());
                int result = search(nums,target);
                System.out.printf("%s\n", result);
            }
        }

        /**
         *  只需要实现此方法，就能调试运行。
         *  注意入参数组的类型。
         */
        public static int search(String[] nums, int target) {
            // write code here
            if (nums == null || nums.length == 0) {
                return -1;
            }
            int start = 0, end = nums.length - 1;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (Integer.parseInt(nums[mid]) < target) {
                    start = mid;
                } else if (Integer.parseInt(nums[mid]) == target) {
                    end = mid;
                } else {
                    end = mid;
                }
            }

            if (Integer.parseInt(nums[start]) == target) {
                return start;
            }
            if (Integer.parseInt(nums[end]) == target) {
                return end;
            }
            return -1;
        }
}
