package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//As we can see in this example, the demarit of renaming a thread in the task definition.
public class CreatingThreadsExecutorSingleThreadExecutor {
    public static void main(String[] args) {

        System.out.println("############## Starting main");

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(new SingleThreadExecutorRunnable());
        executorService.submit(new SingleThreadExecutorRunnable());
        executorService.submit(new SingleThreadExecutorRunnable());
        executorService.submit(new SingleThreadExecutorRunnable());
        executorService.submit(new SingleThreadExecutorRunnable());
        executorService.submit(new SingleThreadExecutorRunnable());

        System.out.println("----------------- Shutting Down Executor Service");
        executorService.shutdown();

        System.out.println("************** Ending main");

    }
}

class SingleThreadExecutorRunnable implements Runnable {

    static int nthInstance = 0;
    private int id;

    public SingleThreadExecutorRunnable() {
        this.id = ++nthInstance;
    }

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


}