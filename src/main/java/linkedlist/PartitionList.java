package linkedlist;

/**
 * 分隔链表
 * 将链表中小于指定值得结点到指定值左侧，大于等于指定值的结点移动到指定值的右侧
 * 1->4->3->2->5->2
 * 3
 * 1->2->2->4->3->5
 *
 * 2->7->9->1->90->45->23
 * 9
 * 2->7->1->9->90->45->23
 */
public class PartitionList {

    /**
     * 最直观的做法，定义左右两个链表leftNode 和 rightNode,
     * 遍历原始链表，小于指定值得结点放入leftNode，大于等于指定值的结点放入rightNode
     * 最后合并左右链表返回
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode dummyNodeLeft = new ListNode(-1);
        ListNode leftNode = dummyNodeLeft;


        ListNode dummyNodeRight = new ListNode(-1);
        ListNode rightNode = dummyNodeRight;

        while (head != null) {
            if (head.value < x) {
                leftNode.next = new ListNode(head.value);
                leftNode = leftNode.next;
            } else {
                rightNode.next = new ListNode(head.value);
                rightNode = rightNode.next;
            }
            head = head.next;
        }

        leftNode.next = dummyNodeRight.next;
        return dummyNodeLeft.next;
    }

    /**
     * 这里要注意的是，要将rightNode的next指针切断，
     * 否则左右相连会有多出的结点
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition1(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode dummyNodeLeft = new ListNode(-1);
        ListNode leftNode = dummyNodeLeft;


        ListNode dummyNodeRight = new ListNode(-1);
        ListNode rightNode = dummyNodeRight;

        while (head != null) {
            if (head.value < x) {
                leftNode.next = head;
                leftNode = leftNode.next;
            } else {
                rightNode.next = head;
                rightNode = rightNode.next;
            }
            head = head.next;
        }
        rightNode.next = null;
        leftNode.next = dummyNodeRight.next;
        return dummyNodeLeft.next;
    }

    public static void main(String[] args) {
        // 2->7->9->1->90->45->23
        // 2->7->1->9->90->45->23
        // 2->1
        // 1->2
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(7);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(1);
        l1.next.next.next.next = new ListNode(90);
        l1.next.next.next.next.next = new ListNode(45);
        l1.next.next.next.next.next.next = new ListNode(23);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(1);

        ListNode partitionedNode = partition1(l1, 9);
        while (partitionedNode != null) {
            System.out.print(partitionedNode.value + " ");
            partitionedNode = partitionedNode.next;
        }
    }
}
