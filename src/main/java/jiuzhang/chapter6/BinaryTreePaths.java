package jiuzhang.chapter6;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找到以root为根节点的所有路径
 * 整棵树路径 = 左子树路径 + 右子树路径
 * @Author: SelectBook
 * @Date: 2022/8/20 21:52
 */
public class BinaryTreePaths {
    
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        
        if (root == null) {
            return paths;
        }
        if (root.left == null && root.right == null) {
            paths.add("" + root.val);
            return paths;
        }
        
        for (String leftPath : binaryTreePaths(root.left)) {
            paths.add(root.val + "->" + leftPath);
        }
        
        for (String rightPath : binaryTreePaths(root.right)) {
            paths.add(root.val + "->" + rightPath);
        }
        return paths;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(Arrays.toString(binaryTreePaths(root).toArray()));
    }
}
