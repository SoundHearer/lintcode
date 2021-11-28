package linkedlist;

public class PrintCommonPart {
    public static void printCommonPart(Node head1, Node head2) {
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
        Node head1 = new Node(2);
        head1.next = new Node(5);
        head1.next.next = new Node(7);
        head1.next.next.next = new Node(9);
        head1.next.next.next.next = new Node(90);

        Node head2 = new Node(1);
        head2.next = new Node(5);
        head2.next.next = new Node(7);
        head2.next.next.next = new Node(9);
        head2.next.next.next.next = new Node(89);

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
