package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 中序遍历
 * 左 根 右
 */
public class InorderTraversal {

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public static void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    /**
     * 前序遍历
     * 根 左 右
     * @param root
     * @param res
     */
    public static void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        inorder(root.left, res);
        inorder(root.right, res);
    }

    /**
     * 后序遍历
     * 左 右 根
     * @param root
     * @param res
     */
    public static void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        inorder(root.right, res);
        res.add(root.val);
    }

    /**
     * 迭代法前序遍历
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    /**
     * 迭代法 前序遍历
     * 根 左 右
     * @param root
     * @return
     */
    public static List<Integer> preOrderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Deque<TreeNode> stack = new LinkedList<>();
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

    /**
     * 迭代法 后序遍历
     * 左 右 根
//     * @param root
     * @return
     */
//    public static List<Integer> postOrderTraversal1(TreeNode root) {
//
//    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(6);
        List<Integer> list = preOrderTraversal1(treeNode);
        for (Integer val: list) {
            System.out.print(val + " ");
        }
    }
}
