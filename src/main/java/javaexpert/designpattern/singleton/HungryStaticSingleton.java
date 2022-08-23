package javaexpert.designpattern.singleton;

/**
 * @Author: SelectBook
 * @Date: 2022/8/22 3:21
 */
public class HungryStaticSingleton {
    
    private static final HungryStaticSingleton hungrySingleton;
    static {
        hungrySingleton = new HungryStaticSingleton();
    }

    private HungryStaticSingleton() {}

    public static HungryStaticSingleton getInstance() {
        return hungrySingleton;
    }
}
