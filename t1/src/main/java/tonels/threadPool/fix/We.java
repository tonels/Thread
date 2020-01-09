package tonels.threadPool.fix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class We {

    public static void main(String[] args) {
//        ExecutorService pool = Executors.newFixedThreadPool(2);
//        ExecutorService pool = Executors.newScheduledThreadPool(2);
//        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService pool = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 50; i++) {

            pool.execute(() -> System.out.println("测试..." + Thread.currentThread().getName()));
//            pool.execute(() -> System.out.println("测试..." + Thread.currentThread().getName()));
        }
        try {
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
