package linkedlist;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

/**
 * 复制带随机指针的链表
 */
public class CopyRandomList {

    Map<Node, Node> cachedNode = new HashMap<>();

    /**
     * 我们用哈希表记录每一个节点对应新节点的创建情况。遍历该链表的过程中，
     * 我们检查「当前节点的后继节点」和「当前节点的随机指针指向的节点」的创建情况。
     * 如果这两个节点中的任何一个节点的新节点没有被创建，我们都立刻递归地进行创建。
     * 当我们拷贝完成，回溯到当前层时，我们即可完成当前节点的指针赋值。
     * 注意一个节点可能被多个其他节点指向，因此我们可能递归地多次尝试拷贝某个节点，
     * 为了防止重复拷贝，我们需要首先检查当前节点是否被拷贝过，
     * 如果已经拷贝过，我们可以直接从哈希表中取出拷贝后的节点的指针并返回即可
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!cachedNode.containsKey(head)) {
            Node headNew = new Node(head.val);
            cachedNode.put(head, headNew);
            headNew.next = copyRandomList(head.next);
            headNew.random = copyRandomList(head.random);
        }
        return cachedNode.get(head);
    }

}
