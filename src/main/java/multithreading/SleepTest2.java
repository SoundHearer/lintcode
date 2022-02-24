package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程在睡眠时拥有的监视器资源不会被释放
 * 每个线程在内部先获取锁，然后睡眠，睡眠结束后会释放锁。
 * 首先，无论你执行多少遍上面的代码都是线程A先输出或者线程B先输出，
 * 不会出现线程A和线程B交叉输出的情况。从执行结果来看，线程A先获取了锁，
 * 那么线程A会先输出一行，然后调用sleep方法让自己睡眠10s，
 * 在线程A睡眠的这10s内那个独占锁lock还是线程A自己持有，
 * 线程B会一直阻塞直到线程A醒来后执行unlock释放锁。
 */
public class SleepTest2 {
    // 创建一个独占锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException{
        // 创建线程A
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取独占锁
                lock.lock();
                try {
                    System.out.println("child threadA is in sleep");
                    Thread.sleep(10000);
                    System.out.println("child threadA is in awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        });

        // 创建线程B
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取独占锁
                lock.lock();
                try {
                    System.out.println("child threadB is in sleep");
                    Thread.sleep(10000);
                    System.out.println("child threadB is in awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        });
        // 启动线程
        threadA.start();
        threadB.start();
    }
}
