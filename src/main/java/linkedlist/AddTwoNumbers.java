package linkedlist;

public class AddTwoNumbers {

    /**
     * 同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加
     * 如果当前两个链表处相应位置的数字为 n1,n2,进位值为 carry，则它们的和为 n1+n2+carry
     * 答案链表处相应位置的数字为 (n1+n2+carry) mod 10，而新的进位值为 [(n1+n2+carry) / 10]
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.value : 0;
            int n2 = l2 != null ? l2.value : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 末位需要添加 1
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode addedListNode = addTwoNumbers(l1, l2);
        while (addedListNode != null) {
            System.out.print(addedListNode.value + " ");
            addedListNode = addedListNode.next;
        }
    }
}
