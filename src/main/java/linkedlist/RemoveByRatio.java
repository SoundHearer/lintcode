package linkedlist;

/**
 * 根据链表长度n，以及a与b的值删除某一个结点
 */
public class RemoveByRatio {

    public ListNode removeByRatio(ListNode head, int a, int b) {
        if (a < 1 || a > b) {
            return head;
        }
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        n = (int) Math.ceil((double) (a * n) / (double) b);
        if (n == 1) {
            head = head.next;
        }
        if (n > 1) {
            cur = head;
            while (--n != 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }
}
