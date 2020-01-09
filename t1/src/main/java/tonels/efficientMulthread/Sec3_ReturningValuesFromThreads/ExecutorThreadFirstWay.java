package tonels.efficientMulthread.Sec3_ReturningValuesFromThreads;

import java.util.concurrent.*;

public class ExecutorThreadFirstWay {
    public static void main (String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "] Started ############################");
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<?> result1 = executorService.submit(new SumTaskExecutor(2, 3, 2000));
        Future<?> result2 = executorService.submit(new SumTaskExecutor(3, 4, 1000));
        Future<?> result3 = executorService.submit(new SumTaskExecutor(4, 5, 500));
        Future<?> result4 = executorService.submit(new LoopTaskRet(5, 6, 4000));
        Future<Integer> result5 = executorService.submit(new LoopTaskRet(6, 7, 5000), 44444);
        try {
            System.out.println("Result1: " + result1.get());
            System.out.println("Result2: " + result2.get());
            System.out.println("Result3: " + result3.get());
            System.out.println("Result4: " + result4.get());
            System.out.println("Result5: " + result5.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        System.out.println("[" + Thread.currentThread().getName() + "] Ended **********************************");
    }
}

class SumTaskExecutor implements Callable<Integer> {

    private static int instanceNumber = 0;
    private int nthTask;
    private String taskId;
    private int a, b;
    private long sleepDuration;

    public SumTaskExecutor(int a, int b, long sleepDuration) {
        this.a = a;
        this.b = b;
        this.sleepDuration = sleepDuration;
        this.nthTask = ++instanceNumber;
        this.taskId = "Task-" + nthTask;
    }

    public Integer call() throws Exception {
        String currentThread = Thread.currentThread().getName();
        System.out.println("[" + currentThread + "] <" + taskId + "> Started #######################");
        TimeUnit.MILLISECONDS.sleep(sleepDuration);
        System.out.println("[" + currentThread + "] <" + taskId + "> Returning ************************");
        return a + b;
    }
}
