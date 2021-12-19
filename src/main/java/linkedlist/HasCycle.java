package linkedlist;

import java.util.HashSet;
import java.util.Set;

public class HasCycle {

    /**
     * 哈希表
     * 使用哈希表来存储所有已经访问过的节点。每次我们到达一个节点
     * 如果该节点已经存在于哈希表中，则说明该链表是环形链表，
     * 否则就将该节点加入哈希表中。重复这一过程，直到我们遍历完整个链表即可
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        while (head != null) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
