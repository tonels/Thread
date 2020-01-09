package tonels.threadPool.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class C1 {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();

        System.out.println(service);

        for (int i = 0; i < 20; i++) {
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);

        service.shutdown();
    }
}


