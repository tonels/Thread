package thread.Executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueExecutor {
    final BlockingQueue<Runnable> queue = new SynchronousQueue<>();
    final AtomicInteger completedTask = new AtomicInteger(0);
    final AtomicInteger rejectedTask = new AtomicInteger(0);
    static long beginTime;
    final int count = 1000;

    final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            10,  // 池中所保存的线程数，包括空闲线程。
            600, // 池中允许的最大线程数。
            30, // 当线程数大于核心线程时，此为终止前多余的空闲线程等待新任务的最长时间
            TimeUnit.SECONDS, // 上个参数的单位
            queue, // workQueue ，执行前用于保持任务的队列，即任务缓存队列
            Executors.defaultThreadFactory(), // threadFactory，
            blockingExecutorHandler); //阻止执行时使用的处理程序因为达到了线程界限和队列容量

    public static final RejectedExecutionHandler blockingExecutorHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
            BlockingQueue<Runnable> queue = executor.getQueue();
            while (true) {
                if (executor.isShutdown()) {
                    throw new RejectedExecutionException("TheadPoolExecutor has shut down!");
                }
                try {
                    if (queue.offer(task, 5000, TimeUnit.MILLISECONDS)) {
                        break;
                    }
                } catch (InterruptedException e) {
                    throw new AssertionError(e);
                }
            }

        }
    };


    public void start() {
        CountDownLatch latch = new CountDownLatch(count);
        CyclicBarrier barrier = new CyclicBarrier(count);
        for (int i = 0; i < count; i++) {
            new Thread(new TestThread(latch, barrier)).start();
        }
    }

    public static void main(String[] args) {
        beginTime = System.currentTimeMillis();
        BlockingQueueExecutor blockingQueueExecutor = new BlockingQueueExecutor();
        blockingQueueExecutor.start();
    }


    class TestThread implements Runnable {
        private CountDownLatch latch;
        private CyclicBarrier barrier;

        public TestThread(CountDownLatch latch, CyclicBarrier barrier) {
            this.latch = latch;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                //barrier的值到达1000时才执行任务
                executor.execute(new Task(latch));
            } catch (RejectedExecutionException e) {
                latch.countDown();
                System.out.println("被拒绝的任务数：" + Thread.currentThread().getName() + "---" + rejectedTask.incrementAndGet());
            }
        }

    }

    class Task implements Runnable {
        private CountDownLatch latch;

        public Task(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行的任务数：" + Thread.currentThread().getName() + "---" + completedTask.incrementAndGet());
            System.out.println("任务耗时为：" + (System.currentTimeMillis() - beginTime) + "ms");
            latch.countDown();
        }
    }


}

