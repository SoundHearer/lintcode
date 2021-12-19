package linkedlist;

public class PrintCommonPart {
    public static void printCommonPart(ListNode head1, ListNode head2) {
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.print(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(5);
        head1.next.next = new ListNode(7);
        head1.next.next.next = new ListNode(9);
        head1.next.next.next.next = new ListNode(90);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(5);
        head2.next.next = new ListNode(7);
        head2.next.next.next = new ListNode(9);
        head2.next.next.next.next = new ListNode(89);

        printCommonPart(head1, head2);

        while (head1 != null) {
            System.out.print(head1.value + " ");
            head1 = head1.next;
        }
        System.out.println();

        while (head2!= null) {
            System.out.print(head2.value + " ");
            head2 = head2.next;
        }
        System.out.println();
    }
}
