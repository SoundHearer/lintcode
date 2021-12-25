package linkedlist;

public class IsPalindrome {

    public static boolean isPalindrome(ListNode head) {
        ListNode pre = null;
        ListNode origin = head;
        ListNode cur = head;

        while (cur != null) {
            // 保留cur的后继结点
            ListNode temp = cur.next;
            // cur指向其前驱结点
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        if (origin == pre) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(2);
        l1.next.next.next = new ListNode(1);

        System.out.print(isPalindrome(l1));
    }
}
