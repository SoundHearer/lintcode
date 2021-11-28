package search;
import java.util.Stack;

/**
 * 仅用栈操作和递归逆序一个栈
 */
public class ReverseStack {

    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(43);
        stack.push(54);
        stack.push(67);
        stack.push(90);
        stack.push(34);
        stack.push(5);

    }
}
