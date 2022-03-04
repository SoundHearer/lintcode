package multithreading;

public class DaemonTest {

    public static void main(String[] args) {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;) {
                }
            }
        });

        // 设置为守护进程
//        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}
