package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的最大深度
 */
public class MaxDepth {
    /**
     * 递归
     * 深度优先搜索
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        // 1. 找出终止条件：当前节点为空
        if (root == null) {
            return 0;
        } else {
            // 某层的执行过程：分别求左右子树的高度的最大值
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            // 2. 找出返回值：节点为空时说明高度为 0，所以返回 0；
            // 节点不为空时则分别求左右子树的高度的最大值，同时加1表示当前节点的高度，返回该数值
            return Math.max(left, right) + 1;
        }
    }

    /**
     * 迭代法
     * 广度优先搜索
     * @param root
     * @return
     */
    public static int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 存放当前层的所有节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                // 每次拓展下一层的时候,我们需要将队列里的所有节点都拿出来进行拓展
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        System.out.print(maxDepth(treeNode));

    }
}
