package linkedlist;

/**
 * 反转部分单链表
 */
public class ReversePart {

    /**
     * 程序员面试指南解法
     * @param head
     * @param from
     * @param to
     * @return
     */
    public static Node reversePart(Node head, int from, int to) {
        // 首先处理边界情况，如果不满足1<=from<=to<=N，直接返回head
        int len = 0;
        Node node1 = head;
        Node fPre = null;
        Node tPos = null;
        while (node1 != null) {
            len++;
            fPre = len == from - 1 ? node1 : fPre;
            tPos = len == to + 1 ? node1: tPos;
            node1 = node1.next;
        }
        if (from > to || from < 1 || to > len) {
            return head;
        }
        // 找到from-1的结点fPre 和 to+1的结点tPos，把需要翻转的部分先翻转，然后正确地连接fPre和tPos
        node1 = fPre == null ? head : fPre.next;
        Node node2 = node1.next;
        node1.next = tPos;
        Node next = null;
        while (node2 != tPos) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        if (fPre != null) {
            fPre.next = node1;
            return head;
        }
        return node1;
    }

    /**
     * leetcode解法 方法一：穿针引线法
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static Node reverseBetween(Node head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        Node dummyNode = new Node(-1);
        dummyNode.next = head;

        Node pre = dummyNode;
        // 第一步：从虚拟头结点走left - 1步，来到left结点的前一个结点
        // 建议写在for循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第二步：从pre再走right - left + 1步，来到right结点
        Node rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第三部：切断出一个字链表（截取链表）
        Node leftNode = pre.next;
        Node cur = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第四步：反转链表子区间
        reverseList(leftNode);

        // 第五步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = cur;
        return dummyNode.next;
    }

    public static void reverseList(Node head) {
        // 也可以使用递归反转一个链表
        Node pre = null;
        Node cur = head;

        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    /**
     * 头插法
     * 穿针引线法的缺点是，如果left和right的区域很大，恰好是链表的头结点和尾结点时，
     * 找到left和right需要遍历一次，反转他们之间的链表还需要遍历一次，
     * 能不能只遍历一次呢？
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static Node reverseBetween1(Node head, int left, int right) {
        // 创建虚拟结点
        Node dummyNode = new Node(-1);
        dummyNode.next = head;
        // 定义结点pre, cur和next
        Node pre = dummyNode;
        // 需要让pre走到待反转部分的前一个结点，即left - 1的位置
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        Node cur = pre.next;
        Node next;

        for (int i = 0; i < right - left; i++) {
            // 先将 cur 的下一个节点记录为 next
            next = cur.next;
            // 把 cur 的下一个节点指向 next 的下一个节点
            cur.next = next.next;
            // 把 next 的下一个节点指向 pre 的下一个节点
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(3);
        head.next.next = new Node(4);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(6);
        Node node = reverseBetween1(head, 2, 4);
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }

    }
}
