package javaexpert.designpattern.singleton;

/**
 * @Author: SelectBook
 * @Date: 2022/8/22 3:30
 */
public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton instance;
    private LazyDoubleCheckSingleton() {}
    
    public static LazyDoubleCheckSingleton getInstance() {
        // 检查是否要阻塞
        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                // 检查是否要重新创建实例
                if (instance == null) {
                    instance = new LazyDoubleCheckSingleton();
                    // 指令重排序的问题
                }
            }
        }
        return instance;
    }
}
