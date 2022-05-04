package multithreading;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

/**
 *
 */
public class UnSafeTest {
    static final Unsafe unsafe;
    static final long stateOffset;
    private volatile long state = 0;
    static {
        try {
            // 使用反射获取UnSafe的成员变量theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 设置为可存取
            field.setAccessible(true);
            // 获取该变量的值
            unsafe = (Unsafe) field.get(null);
            // 获取state在UnSafeTest中的偏移量
            stateOffset = unsafe.objectFieldOffset(UnSafeTest.class.getDeclaredField("state"));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw new Error(ex);
        }
    }
    public static void main(String[] args) {
        UnSafeTest test = new UnSafeTest();
        Boolean success = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(success);
    }
}
