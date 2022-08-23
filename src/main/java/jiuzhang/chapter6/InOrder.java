package jiuzhang.chapter6;

import tree.TreeNode;

import java.util.*;

/**
 * 中序遍历 左 -> 根 -> 右
 * @Author: SelectBook
 * @Date: 2022/8/21 2:29
 */
public class InOrder {

    /**
     * 递归方式
     * @param root
     * @return
     */
    public static List<Integer> inOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        order(root, result);
        return result;
    }
    
    private static void order(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        
        order(root.left, result);
        result.add(root.val);
        order(root.right, result);
    }

    /**
     * 非递归方式 左 -> 根 -> 右
     * 首先访问左子树，将左子树存入栈中，每次将栈顶元素存入结果
     * 如果右子树为空，取出栈顶元素，如果当前元素为栈顶元素右子树，
     * 一直弹出至当前元素不为栈顶元素右子树（此处说明 访问右子树，根节点已经被访问过，弹出即可）
     * 如果结点右子树不为空，访问右子树，继续循环遍历左子树，存入栈中
     * @param root
     * @return
     */
    public static List<Integer> inOrderNonRecruisive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            result.add(node.val);
            
            if (node.right == null) {
                node = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) {
                    node = stack.pop();
                }
            } else {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        return result;
    }

    /**
     * leetcode解法
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(Arrays.toString(inorderTraversal(root).toArray()));
    }
}
