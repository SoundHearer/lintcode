package javaexpert.designpattern.singleton;

/**
 * @Author: SelectBook
 * @Date: 2022/8/22 3:23
 */
public class LazySimpleSingleton {
    private LazySimpleSingleton() {}
    
    // 静态块，公共内存区域
    private static LazySimpleSingleton lazy = null;
    
    public static LazySimpleSingleton getInstance() {
        if (lazy == null) {
            lazy = new LazySimpleSingleton();    
        }
        return lazy;
    }
}
