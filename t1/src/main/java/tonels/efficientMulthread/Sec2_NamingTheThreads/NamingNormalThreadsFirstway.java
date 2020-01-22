package tonels.efficientMulthread.Sec2_NamingTheThreads;

import java.util.concurrent.TimeUnit;

/**
 * ************************* [main] Started
 * ************************* [main] Ended
 * ######################### [CustomThreadName <FirstWay-0>] named thread has  started the task: <FirstWay-0>
 * [CustomThreadName <FirstWay-0>] thread executes task< <FirstWay-0> Tick Tick: 1
 * ######################### [CustomThreadName <FirstWay-1>] named thread has  started the task: <FirstWay-1>
 * [CustomThreadName <FirstWay-1>] thread executes task< <FirstWay-1> Tick Tick: 1
 * [CustomThreadName <FirstWay-0>] thread executes task< <FirstWay-0> Tick Tick: 2
 * [CustomThreadName <FirstWay-1>] thread executes task< <FirstWay-1> Tick Tick: 2
 * [CustomThreadName <FirstWay-0>] thread executes task< <FirstWay-0> Tick Tick: 3
 * [CustomThreadName <FirstWay-1>] thread executes task< <FirstWay-1> Tick Tick: 3
 * [CustomThreadName <FirstWay-1>] thread executes task< <FirstWay-1> Tick Tick: 4
 * [CustomThreadName <FirstWay-1>] thread executes task< <FirstWay-1> Tick Tick: 5
 * [CustomThreadName <FirstWay-0>] thread executes task< <FirstWay-0> Tick Tick: 4
 * [CustomThreadName <FirstWay-0>] thread executes task< <FirstWay-0> Tick Tick: 5
 * ######################### [CustomThreadName <FirstWay-1>] thread has finished executing task: <FirstWay-1>
 * ######################### [CustomThreadName <FirstWay-0>] thread has finished executing task: <FirstWay-0>
 */
public class NamingNormalThreadsFirstway {

    public static void main(String[] args) {

        String threadName = Thread.currentThread().getName();
        System.out.println("************************* [" + threadName + "] Started");
        new Thread(new FirstWay()).start();
        new Thread(new FirstWay()).start();
        System.out.println("************************* [" + threadName + "] Ended");
    }
}

class FirstWay implements Runnable {

    private static int numberOfInstances = 0;
    private String taskId;

    public FirstWay() {
        this.taskId = "FirstWay-" + numberOfInstances++;
    }

    public void run() {
//        Thread.currentThread().setName("CustomThreadName");
        Thread.currentThread().setName("CustomThreadName <" + taskId + ">");
        String threadName = Thread.currentThread().getName();
        System.out.println("######################### [" + threadName + "] named thread has  started the task: <" + taskId + ">");

        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + threadName + "] thread executes task< <" + taskId + "> Tick Tick: " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("######################### [" + threadName + "] thread has finished executing task: <" + taskId + ">");
    }


}