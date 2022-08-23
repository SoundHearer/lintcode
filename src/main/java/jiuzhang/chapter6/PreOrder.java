package jiuzhang.chapter6;

import tree.TreeNode;

import java.util.*;

/**
 * 二叉树的前序遍历 根 -> 左 -> 右
 * @Author: SelectBook
 * @Date: 2022/8/21 1:18
 */
public class PreOrder {

    /**
     * 递归方式
     * @param root
     */
    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        order(root, result);
        return result;
    }
    
    private static void order(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        // 注意这一句
        result.add(root.val);
        order(root.left, result);
        order(root.right, result);
    }

    /**
     * 非递归前序遍历
     * @param root
     */
    public static List<Integer> preOrderNonRecruisive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * leetcode解法
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
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
        System.out.println(Arrays.toString(preOrderNonRecruisive(root).toArray()));
    }
}
