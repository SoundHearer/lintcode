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
    public static ListNode reversePart(ListNode head, int from, int to) {
        // 首先处理边界情况，如果不满足1<=from<=to<=N，直接返回head
        int len = 0;
        ListNode listNode1 = head;
        ListNode fPre = null;
        ListNode tPos = null;
        while (listNode1 != null) {
            len++;
            fPre = len == from - 1 ? listNode1 : fPre;
            tPos = len == to + 1 ? listNode1 : tPos;
            listNode1 = listNode1.next;
        }
        if (from > to || from < 1 || to > len) {
            return head;
        }
        // 找到from-1的结点fPre 和 to+1的结点tPos，把需要翻转的部分先翻转，然后正确地连接fPre和tPos
        listNode1 = fPre == null ? head : fPre.next;
        ListNode listNode2 = listNode1.next;
        listNode1.next = tPos;
        ListNode next = null;
        while (listNode2 != tPos) {
            next = listNode2.next;
            listNode2.next = listNode1;
            listNode1 = listNode2;
            listNode2 = next;
        }
        if (fPre != null) {
            fPre.next = listNode1;
            return head;
        }
        return listNode1;
    }

    /**
     * leetcode解法 方法一：穿针引线法
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyListNode = new ListNode(-1);
        dummyListNode.next = head;

        ListNode pre = dummyListNode;
        // 第一步：从虚拟头结点走left - 1步，来到left结点的前一个结点
        // 建议写在for循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第二步：从pre再走right - left + 1步，来到right结点
        ListNode rightListNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightListNode = rightListNode.next;
        }

        // 第三部：切断出一个字链表（截取链表）
        ListNode leftListNode = pre.next;
        ListNode cur = rightListNode.next;

        // 注意：切断链接
        pre.next = null;
        rightListNode.next = null;

        // 第四步：反转链表子区间
        reverseList(leftListNode);

        // 第五步：接回到原来的链表中
        pre.next = rightListNode;
        leftListNode.next = cur;
        return dummyListNode.next;
    }

    public static void reverseList(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
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
    public static ListNode reverseBetween1(ListNode head, int left, int right) {
        // 创建虚拟结点
        ListNode dummyListNode = new ListNode(-1);
        dummyListNode.next = head;
        // 定义结点pre, cur和next
        ListNode pre = dummyListNode;
        // 需要让pre走到待反转部分的前一个结点，即left - 1的位置
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;

        for (int i = 0; i < right - left; i++) {
            // 先将 cur 的下一个节点记录为 next
            next = cur.next;
            // 把 cur 的下一个节点指向 next 的下一个节点
            cur.next = next.next;
            // 把 next 的下一个节点指向 pre 的下一个节点
            next.next = pre.next;
            pre.next = next;
        }
        return dummyListNode.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(6);
        ListNode listNode = reverseBetween1(head, 2, 4);
        while (listNode != null) {
            System.out.print(listNode.value + " ");
            listNode = listNode.next;
        }

    }
}
