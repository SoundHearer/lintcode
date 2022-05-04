package multithreading;

import java.util.concurrent.ThreadLocalRandom;

public class RandomTest {
    public static void main(String[] args) {
        // 创建一个默认种子的随机数生成器
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // 输出10个在0-5之间的随机数
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
    }
}
