package linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 插入排序重排链表
 */
public class InsertionSortList {

    /**
     * 借助ArrayList存储重排序的链表，再构造出链表
     * @param head
     * @return
     */
    public static ListNode insertionSortList(ListNode head) {
        // 将链表的值存入array
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }
        // 插入排序
        sort(list);
        // 构造链表
        ListNode dummyNode = new ListNode(-1);
        ListNode head1 = dummyNode;

        for (int i = 0; i < list.size(); i++) {
            ListNode node = new ListNode(list.get(i));
            head1.next = node;
            head1 = head1.next;
        }
        return dummyNode.next;
    }

    public static void sort(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (list.get(j) < list.get(j - 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, temp);
                }
            }
        }
    }

    /**
     * 从前往后找插入点 原地重排链表
     * @param head
     * @return
     */
    public static ListNode insertionSortList1(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        // 维护 lastSorted 为链表的已排序部分的最后一个节点，初始时 lastSorted = head
        // 维护 curr 为待插入的元素，初始时 curr = head.next
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.value <= curr.value) {
                //若 lastSorted.val <= curr.val，说明 curr 应该位于 lastSorted 之后，
                // 将 lastSorted 后移一位，curr 变成新的 lastSorted。
                lastSorted = lastSorted.next;
            } else {
                //否则，从链表的头节点开始往后遍历链表中的节点，寻找插入 curr 的位置。
                // 令 prev 为插入 curr 的位置的前一个节点，进行如下操作，完成对 curr 的插入：
                ListNode prev = dummyNode;
                while (prev.next.value <= curr.value) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(1);
        l1.next.next.next = new ListNode(3);

//        List<Integer> list = new ArrayList<>();
//        list.add(4);
//        list.add(2);
//        list.add(1);
//        list.add(3);
//
//        sort(list);
//        for (Integer value: list) {
//            System.out.print(value + " ");
//        }

        ListNode sortedList = insertionSortList(l1);

        while (sortedList != null) {
            System.out.print(sortedList.value + " ");
            sortedList = sortedList.next;
        }
    }
}
