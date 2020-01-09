package tonels.efficientMulthread.Sec3_ReturningValuesFromThreads;

import java.util.concurrent.*;

public class ExecutorThreadSecondWay {
    public static void main (String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5, new NamedThreadFactory());
        CompletionService<TaskDefinition<String, Integer>> completionService = new ExecutorCompletionService<TaskDefinition<String, Integer>>(executorService);

        completionService.submit(new SumTaskExecutorB(2,3,2000));
        completionService.submit(new SumTaskExecutorB(3,4,1000));
        completionService.submit(new SumTaskExecutorB(4,5,500));
        completionService.submit(new LoopTaskRet(5,6, 100), new TaskDefinition<String, Integer>("LoopTaskRetA", 888));

        executorService.shutdown();

        for(int i = 1; i<=4; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class NamedThreadFactory implements ThreadFactory {
    private static int instanceNumber = 0;
    public Thread newThread(Runnable r) {
        return new Thread(r, ("Thread-" + ++instanceNumber));
    }
}

class SumTaskExecutorB implements Callable<TaskDefinition<String, Integer>> {

    private static int instanceNumber = 0;
    private int nthTask;
    private String taskId;
    private int a, b;
    private long sleepDelay;

    public TaskDefinition<String, Integer> call() throws Exception {
        TimeUnit.MILLISECONDS.sleep(sleepDelay);
        return new TaskDefinition<String, Integer>(taskId, a+b);
    }

    public SumTaskExecutorB(int a, int b, long sleepDelay) {
        this.a = a;
        this.b = b;
        this.sleepDelay = sleepDelay;
        this.nthTask = ++instanceNumber;
        this.taskId = "SumTaskExecutor-"+nthTask;
    }
}

class TaskDefinition<S, R> {
    S taskId;
    R result;

    public TaskDefinition(S taskId, R result) {
        this.taskId = taskId;
        this.result = result;
    }

    @Override
    public String toString() {
        return "TaskDefinition{" +
                "taskId=" + taskId +
                ", result=" + result +
                '}';
    }
}