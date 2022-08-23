package javaexpert.designpattern.singleton;

/**
 * 单例模式的通用写法
 * @Author: SelectBook
 * @Date: 2022/8/22 3:12
 */
public class Client {
    public static void main(String[] args) {
        Singleton.getInstance();
    }
    
    static class Singleton {
        private static final Singleton instance = new Singleton();
        
        private Singleton() {}
        
        public static Singleton getInstance() {
            return instance;
        }
    }
}
