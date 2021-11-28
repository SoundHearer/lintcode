package util;

public class MyStack<Item> {
    private Node first;
    private int N;

    public MyStack() {
        first = new Node();
        N = 0;
    }

    public class Node<Item> {
        Item item;
        Node next;
    }

    /**
     * 向链表头部插入一个结点
     * @param item
     */
    public void push(Item item) {
        // 保存指向链表的链接
        Node oldFirst = first;
        // 创建新的首结点
        first = new Node();
        // 设置新结点的实例变量
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    /**
     * 从链表尾部删除一个结点
     * @return
     */
    public Item pop() {
        Item item = (Item) first.item;
        first = first.next;
        N--;
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    /**
     * 打印所有结点
     * @param myStack
     */
    public void printStack(MyStack myStack) {
        String items = "";
        while (!isEmpty()) {
            items = items += myStack.pop() + " ";
        }
        System.out.println(items);
    }

    /**
     * 遍历结点
     */
    public void traverse() {
        for (Node x = first; x != null; x = x.next) {
            System.out.println(x.item);
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(4);
        myStack.push(34);
        myStack.push(13);
        myStack.push(4553);
        myStack.push(54);
        myStack.printStack(myStack);
    }


}
