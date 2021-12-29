package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你两棵二叉树的根节点 p 和 q，检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class IsSameTree {

    /**
     * 深度优先搜索
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            // 如果两个二叉树都为空，则两个二叉树相同。
            return true;
        } else if (p == null || q == null) {
            // 如果两个二叉树中有且只有一个为空，则两个二叉树一定不相同。
            return false;
        } else if (p.val != q.val) {
            // 如果两个二叉树都不为空，那么首先判断它们的根节点的值是否相同，若不相同则两个二叉树一定不同
            return false;
        } else {
            // 若相同，再分别判断两个二叉树的左子树是否相同以及右子树是否相同
            return isSameTree(p.left, p.right) && isSameTree(p.right, q.right);
        }
    }

    /**
     * 广度优先搜索
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        // 使用两个队列分别存储两个二叉树的节点
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        // 初始时将两个二叉树的根节点分别加入两个队列。
        queue1.offer(p);
        queue2.offer(q);
        // 每次从两个队列各取出一个节点，进行如下比较操作
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            // 比较两个节点的值，如果两个节点的值不相同则两个二叉树一定不同；
            if (node1.val != node2.val) {
                return false;
            }
            // 如果两个节点的值相同，则判断两个节点的子节点是否为空,
            TreeNode left1 = node1.left, right1 = node1.right, left2 = node2.left, right2 = node2.right;
            // 如果只有一个节点的左子节点为空
            if (left1 == null ^ left2 == null) {
                return false;
            }
            // 或者只有一个节点的右子节点为空, 则两个二叉树的结构不同，因此两个二叉树一定不同；
            if (right1 == null ^ right2 == null) {
                return false;
            }
            if (left1 != null) {
                queue1.offer(left1);
            }
            if (right1 != null) {
                queue1.offer(right1);
            }
            // 如果两个节点的子节点的结构相同，则将两个节点的非空子节点分别加入两个队列，
            // 子节点加入队列时需要注意顺序，如果左右子节点都不为空，则先加入左子节点，后加入右子节点。
            if (left2 != null) {
                queue2.offer(left2);
            }
            if (right2 != null) {
                queue2.offer(right2);
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
