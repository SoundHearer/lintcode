package jiuzhang.chapter6;

import tree.TreeNode;

/**
 * 给定一棵二叉树，判断是否是平衡的
 * 平衡二叉树定义：任意节点左右子树高度之差不超过1
 * @Author: SelectBook
 * @Date: 2022/8/20 22:29
 */
public class BalancedBinaryTree {
    
    static class Result {
        public boolean isBalanced;
        public int height;
        public Result(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "isBalanced=" + isBalanced +
                    ", height=" + height +
                    '}';
        }
    }
    
    private static Result divideConquer(TreeNode root) {
        // 出口：空树的时候，返回isBalanced=true, height = 0
        if (root == null) {
            return new Result(true, 0);
        }
        
        // 拆解：拆解到左右两边得到左右子树的平衡信息和高度信息
        Result leftResult = divideConquer(root.left);
        Result rightResult = divideConquer(root.right);
        
        int height = Math.max(leftResult.height, rightResult.height) + 1;
        boolean isBalanced = false;
        
        if (!leftResult.isBalanced || !rightResult.isBalanced) {
            isBalanced = false;
        }
        if (Math.abs(leftResult.height - rightResult.height) > 1) {
            isBalanced = false;
        }
        
        return new Result(isBalanced, height);
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        
        System.out.println(divideConquer(root));
    }
}
