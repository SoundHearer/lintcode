package multithreading;

/**
 * Thread.yield（）方法生效了，三个线程分别在i=0时调用了Thread.yield（）方法，
 * 所以三个线程自己的两行输出没有在一起，因为输出了第一行后当前线程让出了CPU执行权。
 *
 * sleep与yield方法的区别在于，当线程调用sleep方法时调用线程会被阻塞挂起指定的时间，
 * 在这期间线程调度器不会去调度该线程。而调用yield方法时，
 * 线程只是让出自己剩余的时间片，并没有被阻塞挂起，
 * 而是处于就绪状态，线程调度器下一次调度时就有可能调度到当前线程执行
 */
public class YieldTest implements Runnable{

    YieldTest() {
        // 创建并启动线程
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            // 当i=0时让出cpu执行权，放弃时间片，进行下一轮调度
            if ((i % 5) == 0) {
                System.out.println(Thread.currentThread() + "yield cpu...");
                // 当前线程让出cpu执行权，放弃时间片，进行下一轮调度
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread() + "is over");
    }

    public static void main(String[] args) {
        // 开启了三个线程，每个线程的功能都一样，都是在for循环中执行5次打印
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }
}
