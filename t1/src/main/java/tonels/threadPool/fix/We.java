package tonels.threadPool.fix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class We {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 50; i++) {
            pool.execute(new Run1());
        }

        try {
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("执行完毕");
    }

}

class Run1 implements Runnable {

    static int nthInstance = 0;

    private int id;

    public Run1() {
        this.id = ++nthInstance;
    }

    @Override
    public void run() {
        System.out.println("测试..." + Thread.currentThread().getName() + "_" + id);
    }
}
