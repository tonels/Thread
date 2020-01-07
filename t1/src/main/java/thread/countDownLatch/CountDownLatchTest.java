package thread.countDownLatch;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class CountDownLatchTest {


    // ----------------------------------- SS ----------------
    static CountDownLatch c = new CountDownLatch(2);
    /**
     * CountDownLatch
     * @throws InterruptedException
     * Thread-1 ::1
     * Thread-2 ::1
     * main3
     */
    @Test
    public void t1() throws InterruptedException {

        new Thread(new Thread1()).start();
        new Thread(new Thread1()).start();

        //await会一直阻塞到计数器为零，或者等待中的线程中断，或者等待超时
        c.await();
        System.out.println(Thread.currentThread().getName() + 3);
    }

    class Thread1 implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " ::" + 1);
            c.countDown();
        }
    }

// ----------------------------------- SS ----------------

    // ----------------------------------- SS ----------------

    /**
     * @throws InterruptedException
     * Thread-1, 此处处理任务细节...
     * Thread-3, 此处处理任务细节...
     * Thread-2, 此处处理任务细节...
     * 1000000 纳秒
     */
    @Test
    public void t2() throws InterruptedException {
        try {
            this.timeTask(3, new TaskRunnable());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long timeTask(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    try {
                        startGate.await();
                    } finally {
                        endGate.countDown();
                    }
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
        LocalDateTime start = LocalDateTime.now();
        startGate.countDown();
        endGate.await();
        LocalDateTime end = LocalDateTime.now();
        System.out.println(ChronoUnit.NANOS.between(start,end) + " 纳秒");
        return ChronoUnit.NANOS.between(start,end);
    }

    class TaskRunnable implements Runnable {
        public void run() {
            System.out.println(Thread.currentThread().getName() + ", 此处处理任务细节...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子任务已完成");
        }
    }
// ----------------------------------- SS ----------------
// ----------------------------------- SS ----------------

    /**
     * todo 思考，为什么 t2(),和t3() 输出结果不一样
     * Thread-1, 此处处理任务细节...
     * Thread-2, 此处处理任务细节...
     * Thread-3, 此处处理任务细节...
     * 子任务已完成
     * 子任务已完成
     * 子任务已完成
     * 87000000 纳秒
     * @throws InterruptedException
     */
    @Test
    public void t3() throws InterruptedException {
        this.timeTask_2(3, new TaskRunnable());
    }
    public static long timeTask_2(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                task.run();
                endGate.countDown();
            }).start();
        }
        LocalDateTime start = LocalDateTime.now();
        endGate.await();
        LocalDateTime end = LocalDateTime.now();
        System.out.println(ChronoUnit.NANOS.between(start,end) + " 纳秒");
        return ChronoUnit.NANOS.between(start,end);
    }



}
