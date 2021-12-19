package linkedlist;

public class RemoveElements {

    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = dummyNode;

        while (cur.next != null) {
            if (cur.next.value == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(8);
        l1.next.next.next.next = new ListNode(8);
        l1.next.next.next.next.next = new ListNode(10);

        ListNode removedNode = removeElements(l1, 8);
        while (removedNode != null) {
            System.out.print(removedNode.value + " ");
            removedNode = removedNode.next;
        }
    }
}
