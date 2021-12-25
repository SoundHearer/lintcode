package linkedlist;
import java.util.Random;

/**
 * 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
 */
public class GetRandom {

    /**
     * 蓄水池抽样算法
     * @param head
     * @return
     */
    public int getRandom(ListNode head) {
        int reserve = 0;
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            int r = new Random().nextInt(count)+1;
            if (r == count) {
                reserve = cur.value;
            }
            cur = cur.next;
        }
        return reserve;
    }
}
