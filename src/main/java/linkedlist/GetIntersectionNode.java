package linkedlist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给你两个单链表的头节点 headA 和 headB，
 * 请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null.
 * 1->9->1->2->4
 * 3->2->4
 * 返回2
 *
 * 2->6->4
 * 1->5
 * 返回null
 */
public class GetIntersectionNode {

    /**
     * 哈希集合
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode GetIntersectionNode(ListNode headA, ListNode headB) {
        // 首先遍历链表headA，并将链表headA 中的每个节点加入哈希集合中。
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        // 然后遍历链表headB，对于遍历到的每个节点，判断该节点是否在哈希集合中
        temp = headB;
        while (temp != null) {
            // 如果当前节点在哈希集合中，则后面的节点都在哈希集合中，
            // 即从当前节点开始的所有节点都在两个链表的相交部分，
            // 因此在链表headB 中遍历到的第一个在哈希集合中的节点就是两个链表相交的节点，返回该节点。
            if (visited.contains(temp)) {
                return temp;
            }
            // 如果当前节点不在哈希集合中，则继续遍历下一个节点；
            temp = temp.next;
        }
        // 如果链表headB 中的所有节点都不在哈希集合中，则两个链表不相交，返回 null
        return null;
    }


    /**
     * 双指针
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode GetIntersectionNode1(ListNode headA, ListNode headB) {
        // 只有当链表 headA 和headB 都不为空时，两个链表才可能相交。因此首先判断链表headA和headB是否为空，
        // 如果其中至少有一个链表为空，则两个链表一定不相交，返回null。
        if (headA == null || headB == null) {
            return null;
        }
        // 当链表 headA 和 headB都不为空时，创建两个指针pA和pB，
        // 初始时分别指向两个链表的头节点headA 和headB，然后将两个指针依次遍历两个链表的每个节点
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            // 每步操作需要同时更新指针pA 和 pB。
            // 如果指针 pA 不为空，则将指针pA 移到下一个节点；如果指针pB 不为空，则将指针pB 移到下一个节点。
            // 如果指针 pA 为空，则将指针pA 移到链表headB 的头节点；如果指针pB 为空，则将指针pB 移到链表headA 的头节点。
            // 当指针pA 和pB指向同一个节点或者都为空时，返回它们指向的节点或者null
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(1);
        l1.next.next.next = new ListNode(2);
        l1.next.next.next = new ListNode(2);
    }

}
