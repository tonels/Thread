package tonels.efficientMulthread.Sec2_NamingTheThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class NamingExecutorThreads {
    public static void main (String[] args) {
        System.out.println("################# Starting Main ");

        ExecutorService executorService = Executors.newCachedThreadPool(new MyThreadFactory());
        executorService.execute(new LoopTask());
        executorService.execute(new LoopTask());
        executorService.execute(new LoopTask());
        executorService.execute(new LoopTask());
        executorService.execute(new LoopTask());

        executorService.shutdown();
        System.out.println("******************* Ending Main ");
    }
}

class LoopTask implements Runnable {
    private static int instanceCount = 1;
    private int nthTask;

    public void run() {
        System.out.println("###################### Starting [" + Thread.currentThread().getName() + "] <Task-"+nthTask+">");

        for(int i = 1; i <=3; i++) {
            System.out.println("["+Thread.currentThread().getName()+"] <Task-"+nthTask+"> Tick Tick: " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long)(Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("********************** Ending [" + Thread.currentThread().getName() + "] <Task-"+nthTask+">");

    }

    public LoopTask() {
        this.nthTask = instanceCount++;
    }
}

class MyThreadFactory implements ThreadFactory {
    private static int instanceCount = 1;
    private int nthThread;
    public Thread newThread(Runnable r) {
//        Thread t = new Thread(r, "MyThread-" + nthThread);    //Wont work. Will always return a thread with name "MyThread-1", since the constructor of MyThreadFactory is only called once, while every time a new thread is created, it is created using newThread(Runnable r) method itself (i.e., this method only). Hence, the constructor is never called. While in cases of tasks, every time we would have to send a new LoopTask, thus invoking the constructor on every instance creation.
        Thread t = new Thread(r, "MyThread-" + (instanceCount++));
        return t;
    }

    public MyThreadFactory() {
        this.nthThread = instanceCount++;
    }
}