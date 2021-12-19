package linkedlist;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点
 *
 */
public class RemoveNthFromEnd {

    /**
     * 最直接的想法是遍历一遍算出链表长度 M
     * 倒数第 N个，就是顺数下标第 M - N - 1 个
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 定义链表长度
        int m = 0;

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode dummyNode1 = new ListNode(-1);
        dummyNode1.next = head;

        ListNode cur = dummyNode;
        ListNode cur1 = dummyNode1;

        while (cur.next != null) {
            m++;
            cur = cur.next;
        }

        for (int i = 0; i <= m - n && cur1.next != null; i++) {
            if (i == m - n) {
                cur1.next = cur1.next.next;
            } else {
                cur1 = cur1.next;
            }
        }
        return dummyNode1.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(8);
        l1.next.next.next.next = new ListNode(8);
        l1.next.next.next.next.next = new ListNode(10);

        ListNode removedNode = removeNthFromEnd(l1, 1);
        while (removedNode != null) {
            System.out.print(removedNode.value + " ");
            removedNode = removedNode.next;
        }
    }
}
