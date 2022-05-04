package multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;

public class ThreadTest {

    static Queue queue = new LinkedList();
    static int MAX_SIZE = 1000;

    public static class MyThread extends Thread {
        // 继承Thread类并重写run方法
        @Override
        public void run() {
            // run方法执行完毕，该线程就处于终止状态
            System.out.println("I am a child thread");
        }
    }

    public static class RunnableTask implements Runnable {
        @Override
        public void run() {
            // run方法执行完毕，该线程就处于终止状态
            System.out.println("I am a child thread");
        }
    }

    // 创建任务类，类似Runnable
    public static class CallerTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "hello";
        }
    }

    public static void main(String[] args) throws InterruptedException{
//        // 创建线程
//        MyThread myThread = new MyThread();
//        // 启动线程，并没有马上执行，而是出于就绪状态
//        myThread.start();
//        RunnableTask task = new RunnableTask();
//        new Thread(task).start();
//        new Thread(task).start();
        // 创建异步任务
//        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
//        // 启动线程
//        new Thread(futureTask).start();
//        try {
//            // 等待任务执行完毕，并返回结果
//            String result = futureTask.get();
//            System.out.println(result);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        // 生产线程
        synchronized (queue) {
            // 消费队列满，则等待队列空闲
            while (queue.size() == MAX_SIZE) {
                try {
                    // 挂起当前线程，并释放通过同步块获取的queue上的锁，
                    // 让消费者线程可以获取该锁，然后获取队列里面的元素
                    queue.wait();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 空闲则生成元素，并通知消费者线程
            queue.add(12);
            queue.notify();
        }
        // 消费者线程
        synchronized (queue) {
            // 消费队列为空
            while (queue.size() == 0) {
                try {
                    // 挂起当前线程，并释放通过同步块获取到的queue上的锁，
                    // 让生产者线程可以获取该锁，将生产元素放入队列
                    queue.wait();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 消费元素，并通知唤醒生产者线程
            queue.poll();
            queue.notifyAll();
        }
    }
}
