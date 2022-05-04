package multithreading;

/**
 * 死锁
 */
public class DeadLockTest2 {
    // 创建资源
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        // 创建线程A
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get ResourceA");
                    try {
                        // 休眠1s是为了保证线程A在获取resourceB对应的锁前让线程B抢占到CPU，
                        // 获取到资源resourceB上的锁
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get sourceB");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + "get resourceB");
                    }
                }
            }
        });

        // 创建线程B
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + " get ResourceB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get sourceA");
                    synchronized (resourceA) {
                        System.out.println(Thread.currentThread() + "get resourceA");
                    }
                }
            }
        });

//        // 创建线程B
//        Thread threadB = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (resourceA) {
//                    System.out.println(Thread.currentThread() + " get ResourceB");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread() + "waiting get sourceA");
//                    synchronized (resourceB) {
//                        System.out.println(Thread.currentThread() + "get resourceA");
//                    }
//                }
//            }
//        });

        // 启动线程
        threadA.start();
        threadB.start();
    }
}
