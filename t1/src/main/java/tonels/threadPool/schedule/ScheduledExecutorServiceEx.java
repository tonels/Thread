package tonels.threadPool.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceEx {


    public static void main(String[] args) {
//        t1();
//        t2();
        t3();

    }


    public static void t1() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        Runnable task2 = () ->
                System.out.println("Running task2...");

        task1();
        //run this task after 5 seconds, nonblock for task3
        ses.schedule(task2, 10, TimeUnit.SECONDS);
        ses.shutdown();
        task3();
    }

    public static void t2() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        Runnable task = () ->
                System.out.println("Running task...");
        ses.scheduleAtFixedRate(task, 5, 2, TimeUnit.SECONDS);
    }

    public static void t3() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable task = () ->
        {
            try {
                System.out.println("Thread prepare...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Running task...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        ses.scheduleWithFixedDelay(task, 5, 2, TimeUnit.SECONDS);
    }

    private static void task1() {
        System.out.println("Running task1...");
    }

    private static void task3() {
        System.out.println("Running task3...");
    }
}

