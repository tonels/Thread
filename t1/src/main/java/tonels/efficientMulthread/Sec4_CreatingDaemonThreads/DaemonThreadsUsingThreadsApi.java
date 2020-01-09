package tonels.efficientMulthread.Sec4_CreatingDaemonThreads;

import java.util.concurrent.TimeUnit;

public class DaemonThreadsUsingThreadsApi {
    public static void main(String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "] STARTING");
        Thread t1 = new Thread(new LoopTask(2000), "Thread-1");
        Thread t2 = new Thread(new LoopTask(1000), "Thread-2");
        t1.setDaemon(true);

        t1.start();
        t2.start();

        System.out.println("[" + Thread.currentThread().getName() + "] STARTING");
    }
}

class LoopTask implements Runnable {

    private static int count = 0;
    private long sleepTime;
    private int instanceNumber;
    private String taskId;

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        String threadType = Thread.currentThread().isDaemon() ? "DAEMON" : "USER";
        System.out.println("[" + threadName + ", " + threadType + "] <" + taskId + "> STARTING");

        for(int i = 10; i > 0; i--) {
            System.out.println("[" + threadName + ", " + threadType + "] <" + taskId + "> TICK TICK " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("[" + threadName + ", " + threadType + "] <" + taskId + "> ENDING");
    }

    public LoopTask(long sleepTime) {
        this.sleepTime = sleepTime;
        this.instanceNumber = ++count;
        this.taskId = "Task-" + instanceNumber;
    }
}
