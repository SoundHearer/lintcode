package jiuzhang.chapter6;

import tree.TreeNode;

import java.util.*;

/**
 * BFS
 * 二叉树的层次遍历
 * @Author: SelectBook
 * @Date: 2022/8/20 22:56
 */
public class LevelOrder {
    
    public static List<List<String>> levelOrder(TreeNode root) {
        List result = new ArrayList();
        
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
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
        
        System.out.println(Arrays.toString(levelOrder(root).toArray()));
    }
    
}
