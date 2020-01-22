package tonels.threadPool.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class C2 {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();

        System.out.println(service);

        for (int i = 0; i < 20; i++) {
            service.execute(() -> {
                try {
//                    TimeUnit.MILLISECONDS.sleep(1000);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println("Main 进程");

        TimeUnit.SECONDS.sleep(3);

        System.out.println(service);

        service.shutdown();
        System.out.println(service);
    }

}
