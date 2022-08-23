package jiuzhang.chapter6;

import tree.TreeNode;

import java.util.*;

/**
 * 二叉树的后序遍历 左 -> 右 -> 根
 *
 * @Author: SelectBook
 * @Date: 2022/8/22 1:36
 */
public class PostOrder {

    /**
     * 递归实现
     * @param root
     * @return
     */
    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        order(root, result);
        return result;
    }

    public static void order(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        order(root.left, result);
        order(root.right, result);
        result.add(root.val);
    }

    /**
     * 非递归 leetcode版本
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }


    /**
     * 非递归 九章算法的版本
     * 
     * 二叉树的后序遍历 题解： 使用栈进行二叉树后序遍历，首先对左子树进行遍历压入栈中，
     * 直至左子树为空，然后访问右子树。故每个节点会被访问两次，当节点被第二次访问时，该节点出栈。
     * @param root
     * @return
     */
    public static ArrayList<Integer> postorderTraversal1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode prev = null; // previously traversed node
        TreeNode curr = root;

        if (root == null) {
            return result;
        }

        stack.push(root);
        while (!stack.empty()) {
            curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) { // traverse down the tree
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else if (curr.left == prev) { // traverse up the tree from the left
                if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else { // traverse up the tree from the right
                result.add(curr.val);
                stack.pop();
            }
            prev = curr;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(Arrays.toString(postorderTraversal(root).toArray()));
    }
}
