package tree;

import java.util.Stack;

/**
 * 深度优先遍历二叉树
 * 迭代法遍历
 */
public class OrderIteration {

    /**
     * 迭代法前序遍历
     * 根 左 右
     * @param head
     */
    public static void preOrderIteration(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 优先将头结点加入Stack，然后打印
        stack.push(head);
        // 应该先打印左子树，然后右子树。所以先加入Stack的就是右子树，然后左子树。
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 迭代法中序遍历
     * @param head
     */
    public static void inOrderIteration(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            // 尽可能的将这个节点的左子树压入Stack，此时栈顶的元素是最左侧的元素，
            // 其目的是找到一个最小单位的子树(也就是最左侧的一个节点)，
            // 并且在寻找的过程中记录了来源，才能返回上层,同时在返回上层的时候已经处理完毕左子树了
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 当处理完最小单位的子树时，返回到上层处理了中间节点。
            // （如果把整个左中右的遍历都理解成子树的话，就是处理完 左子树->中间(就是一个节点)->右子树）
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            // 如果有右节点，其也要进行中序遍历
            if (node.right != null) {
                cur = node.right;
            }
        }
    }
}
