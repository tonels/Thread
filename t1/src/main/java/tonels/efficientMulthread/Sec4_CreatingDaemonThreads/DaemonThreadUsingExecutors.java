package tonels.efficientMulthread.Sec4_CreatingDaemonThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DaemonThreadUsingExecutors {
    public static void main(String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "] STARTING");
        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadFactory());

        executorService.execute(new LoopTask(500));
        executorService.execute(new LoopTask(1000));

        executorService.shutdown();
        System.out.println("[" + Thread.currentThread().getName() + "] ENDING");

    }
}

class NamedThreadFactory implements ThreadFactory {

    private static int count = 0;

    @Override
    public Thread newThread(Runnable runnable) {
        Thread t = new Thread(runnable, "Thread-" + ++count);
        if((count&1) == 0)  {       //even
            t.setDaemon(true);
        }
        return t;
    }
}