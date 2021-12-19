package linkedlist;

import sun.awt.image.ImageWatched;
import sun.reflect.generics.tree.Tree;
import tree.OrderRecur;
import tree.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 将二叉树展开为单链表
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同
 */
public class Flatten {

    /**
     * 前序遍历 递归
     * @param root
     */
    public static void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrderTraversal(root, list);
        int size = list.size();
        // 前序遍历结束之后更新每个节点的左右子节点的信息
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), cur = list.get(i);
            prev.left = null;
            prev.right = cur;
        }
    }

    public static void preOrderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preOrderTraversal(root.left, list);
            preOrderTraversal(root.right, list);
        }
    }

    /**
     * 迭代实现前序遍历
     * @param root
     */
    public static void flatten1(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    /**
     * 前序遍历和展开同步进行
     * @param root
     */
    public static void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                prev.left = null;
                prev.right = curr;
            }
            TreeNode left = curr.left, right = curr.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            prev = curr;
        }
    }

    /**
     * 寻找前驱结点
     * 对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，
     * 作为前驱节点，将当前节点的右子节点赋给前驱节点的右子节点，
     * 然后将当前节点的左子节点赋给当前节点的右子节点，
     * 并将当前节点的左子节点设为空。对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处理结束
     *
     * @param root
     */
    public static void flatten3(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(5);
        head.left.left = new TreeNode(3);
        head.left.right = new TreeNode(4);
//        head.right.left = new TreeNode();
        head.right.right = new TreeNode(6);
//        OrderRecur.preOrderRecur(head);

        flatten1(head);
        OrderRecur.preOrderRecur(head);
    }
}
