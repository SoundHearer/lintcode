package util;

public class MyQueue<Item> {
    private Node first;
    private Node last;
    private int N;

    public MyQueue() {
        first = new Node();
        last = new Node();
        N = 0;
    }
    public class Node<Item> {
        Item item;
        Node next;
    }

    /**
     * 从链表头部删除结点
     * @return
     */
    public Item dequeue() {
        Item item = (Item) first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;
    }

    /**
     * 从链表尾部插入结点
     */
    public void enqueue(Item item) {
        // 保存指向尾结点的链接
        Node oldLast = last;
        // 创建新的尾部结点
        last = new Node();
        last.item = item;
        // 将尾链接指向新结点
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N++;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 遍历结点
     */
    public void traverse() {
        for (Node x = first; x != null; x = x.next) {
            System.out.println(x.item + " ");
        }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.enqueue(45);
        myQueue.enqueue(3);
        myQueue.enqueue(34);
        myQueue.enqueue(67);
        myQueue.enqueue(90);
        myQueue.traverse();
    }
}
