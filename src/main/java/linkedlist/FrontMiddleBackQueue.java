package linkedlist;

import java.util.LinkedList;

/**
 * 设计前中后队列
 */
public class FrontMiddleBackQueue {

    LinkedList<Integer> linkedList  = new LinkedList<>();
    int sum = 0;
    public FrontMiddleBackQueue() {
    }

    public void pushFront(int val) {
        linkedList.addFirst(val);
        sum++;
    }

    public void pushMiddle(int val) {
        int mid = sum / 2;
        linkedList.add(mid, val);
        sum++;
    }

    public void pushBack(int val) {
        linkedList.addLast(val);
        sum++;
    }

    public int popFront() {
        if (linkedList.size() == 0) {
            return -1;
        }
        int z = linkedList.getFirst();
        linkedList.removeFirst();
        sum--;
        return z;
    }

    public int popMiddle() {
        if (linkedList.size() == 0) {
            return -1;
        }
        int mid = sum / 2;
        int z;
        if (sum % 2 == 0) {
            z = linkedList.get(mid - 1);
        } else {
            z = linkedList.get(mid);
        }
        linkedList.remove(linkedList.indexOf(z));
        sum--;
        return z;
    }

    public int popBack() {
        if (linkedList.size() == 0) {
            return -1;
        }
        int z = linkedList.getLast();
        linkedList.removeLast();
        sum--;
        return z;
    }

    public static void main(String[] args) {

    }
}
