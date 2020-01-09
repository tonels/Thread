package tonels.efficientMulthread.Sec3_ReturningValuesFromThreads;


import java.util.concurrent.TimeUnit;

class LoopTaskRet implements Runnable {
    private static int instanceNumber = 0;
    private int nthTask;
    private String taskId;
    private int a, b, sum = 0;
    private long delay;
    private boolean isDone;

    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("##################### [" + threadName + "] <" + taskId + "> STARTING");


        System.out.println("[" + threadName + "] <" + taskId + "> is going to sleep");
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[" + threadName + "] <" + taskId + "> has woken up");
        sum = a + b;
        isDone = true;
        System.out.println("XXXXXXXXXXXXXXXXXXXX [" + threadName + "] About to acquire lock for " + this);
        synchronized (this) {
            notifyAll();
            System.out.println("[" + threadName + "] has notified all the threads that were waiting on this object");
        }
        System.out.println("OOOOOOOOOOOOOOOOOOOOO [" + threadName + "] Released lock for " + this);
        System.out.println("********************* [" + threadName + "] <" + taskId + "> ENDING");
    }

    public int getSum() {
        /*
        Problem if no outside sync block:-
Say that main (consumer) thread enters and finds isDone as false. But then the CPU lets thread1 (producer) thread take control and thread1 (producer) thread then makes isDone as true and notifies the thread waiting on the same lock (which was supposed to be main (consumer) thread). But since main (consumer) thread never even went to wait, thus it will now go to wait (since it is already inside the !isDone if block and would cause endless wait for the main (consumer) thread.)

How does an outside sync solve the problem?
With the outside sync block, the main (consumer) thread would keep the lock when it enters the !isDone if block (critical section). In this way, even if CPU takes out main (consumer) thread and lets thread1 (producer) thread execute, then also thread1 (producer) thread won't be able to notify any thread cause the lock is still with main (consumer) thread. For notifying, thread1 (producer) thread needs the lock which right now is with main (consumer) thread. Thus, this outside sync block makes sure that the main (consumer) thread goes into waiting before the thread1 (producer) thread can notify it.


Thus, whenever we are in a multithreaded environment and we need to work on locks which are the deciding variables for waiting and notifying, we need to make sure that the consumer element (which is going to wait on the other element) never releases lock once it is inside the critical section (checking flag). This is done to ensure that the producer thread (which has to notify after an event), never notifies first. THE PRODUCER THREAD (WHICH HAS TO NOTIFY) ALWAYS HAS TO NOTIFY AFTER THE CONSUMER THREAD (WHICH IS SUPPOSED TO WAIT) IS WAITING. NO CONSUMER THREAD SHOULD GO TO WAIT AFTER PRODUCER NOTIFIES. Thus it is important to keep two sync blocks around the checker flag variable too.

        */
        synchronized (this) {     //outside sync
            if (!isDone) {      //!isDone if block
                System.out.println("[" + Thread.currentThread().getName() + "] SLEEPING");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[" + Thread.currentThread().getName() + "] has woken up");
                synchronized (this) {
                    System.out.println("XXXXXXXXXXXXXXXXXXXX OOOOOOOOOOOOOOOOO [" + Thread.currentThread().getName() + "] going to release the acquired lock and will wait till another thread with " + this + " lock will notify it");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("[" + Thread.currentThread().getName() + "] OUT OF SYNC BLOCK");
            }
        }
        return sum;
    }

    public LoopTaskRet(int a, int b, long delay) {
        this.isDone = false;
        this.delay = delay;
        this.a = a;
        this.b = b;
        this.nthTask = ++instanceNumber;
        this.taskId = "Task-" + nthTask;
    }
}

public class NormalThreadsFirstWay {
    public static void main(String[] args) {
        System.out.println("##################### [" + Thread.currentThread().getName() + "] STARTING");

        LoopTaskRet loopTaskRet1 = new LoopTaskRet(2, 3, 100);
        LoopTaskRet loopTaskRet2 = new LoopTaskRet(3, 4, 1000);
        LoopTaskRet loopTaskRet3 = new LoopTaskRet(4, 5, 500);

        Thread t1 = new Thread(loopTaskRet1, "Thread-1");
        t1.start();

        Thread t2 = new Thread(loopTaskRet2, "Thread-2");
        t2.start();

        Thread t3 = new Thread(loopTaskRet3, "Thread-3");
        t3.start();

        System.out.println("Result1: " + loopTaskRet1.getSum());
        System.out.println("Result2: " + loopTaskRet2.getSum());
        System.out.println("Result3: " + loopTaskRet3.getSum());

        System.out.println("********************* [" + Thread.currentThread().getName() + "] ENDING");
    }
}