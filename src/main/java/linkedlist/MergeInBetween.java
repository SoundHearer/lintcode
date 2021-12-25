package linkedlist;

/**
 * 给你两个链表list1和list2，它们包含的元素分别为n个和m个
 *
 * 请你将list1中下标从a到b的全部节点都删除，并将接在被删除节点的位置
 */
public class MergeInBetween {
    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = list1;

        ListNode first = list1;
        ListNode second = list1;

        if (a == 0) {
            for (int i = 0; i < b + 1; i++) {
                second = second.next;
            }
            dummyNode.next = list2;
        } else {
            for (int i = 0; i < a - 1; i++) {
                first = first.next;
            }
            for (int i = 0; i < b + 1; i++) {
                second = second.next;
            }
            first.next = list2;
        }

        while (list2.next != null) {
            list2 = list2.next;
        }
        list2.next = second;
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(0);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(2);
        l1.next.next.next = new ListNode(3);
        l1.next.next.next.next = new ListNode(4);
        l1.next.next.next.next.next = new ListNode(5);

        int a  = 4, b = 5;

        ListNode l2 = new ListNode(100000);
        l2.next = new ListNode(100001);
        l2.next.next = new ListNode(100002);


        ListNode mergeddNode = mergeInBetween(l1, a, b, l2);
        while (mergeddNode != null) {
            System.out.print(mergeddNode.value + " ");
            mergeddNode = mergeddNode.next;
        }
    }
}
