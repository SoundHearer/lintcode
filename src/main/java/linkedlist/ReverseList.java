package linkedlist;

/**
 * 翻转单向链表
 * 即将链表所有节点的next指针指向它的前驱节点。
 * 由于是单链表，我们在遍历时并不能直接找到其前驱节点，因此我们需要定义一个指针保存其前驱节点。
 * 每次翻转时，我们都需要修改当前节点的next指针，如果不在改变当前节点的next指针前保存其后继节点，
 * 那么我们就失去了当前节点和后序节点的联系，因此还需要额外定义一个指针用于保存当前节点的后继节点。
 *
 * 具体过程如下：
 *
 * 1、定义一个前驱指针pre和cur指针，pre指针用来指向前驱节点，cur指针用来遍历整个链表，初始化pre = null，cur = head。
 * 2.我们首先保存cur指针指向节点的后继节点，然后让cur指针指向节点的next指针指向其前驱节点，即cur->next = pre。
 * 3.pre指针和cur指针分别后移一位，重复上述过程，直到cur指向空节点。
 * 4.最后我们返回pre节点。
 *
 */
public class ReverseList {

    public Node reverse(Node head) {
        Node pre = null;
        Node cur = head;

        while (cur != null) {
            // 保留cur的后继结点
            Node temp = cur.next;
            // cur指向其前驱结点
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        // 最后返回pre
        return pre;
    }
}
