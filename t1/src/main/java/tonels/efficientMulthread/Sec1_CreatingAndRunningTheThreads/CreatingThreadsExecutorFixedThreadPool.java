package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CreatingThreadsExecutorFixedThreadPool {
    public static void main(String[] args) {
        System.out.println("############## Starting main");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new ExecutorFixedThreadPool());
        executorService.submit(new ExecutorFixedThreadPool());
        executorService.submit(new ExecutorFixedThreadPool());
//        executorService.submit(new ExecutorFixedThreadPool());
//        executorService.submit(new ExecutorFixedThreadPool());
//        executorService.submit(new ExecutorFixedThreadPool());

        System.out.println("----------------- Shutting Down Executor Service");
        List<Runnable> runnables = executorService.shutdownNow();
        executorService.submit(new ExecutorFixedThreadPool());
        System.out.println("Number of tasks that were awaiting execution or were not even started: " + runnables.size());
        System.out.println("************** Ending main");

    }
}

class ExecutorFixedThreadPool implements Runnable {

    static int nthInstance = 0;
    private int id;

    public void run() {
        System.out.println("######## Starting <Thread-" + id + ">");

        for(int i = 1; i <= 3; i++) {
            System.out.println("<Thread-" + id + "> Tick Tick: " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXX <Thread-" + id + "> is Interrupted.");
            }
        }

        System.out.println("********** Ending <Thread-" + id + ">");
    }

    public ExecutorFixedThreadPool() {
        this.id = ++nthInstance;
    }
}
