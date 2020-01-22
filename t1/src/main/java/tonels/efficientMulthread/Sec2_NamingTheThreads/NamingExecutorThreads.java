package tonels.efficientMulthread.Sec2_NamingTheThreads;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * ******************* Starting Main
 * ******************* Ending Main
 * ###################### Starting [MyThread-1] <Task-1>
 * [MyThread-1] <Task-1> Tick Tick: 1
 * ###################### Starting [MyThread-2] <Task-2>
 * [MyThread-2] <Task-2> Tick Tick: 1
 * ###################### Starting [MyThread-3] <Task-3>
 * [MyThread-3] <Task-3> Tick Tick: 1
 * ###################### Starting [MyThread-4] <Task-4>
 * [MyThread-4] <Task-4> Tick Tick: 1
 * ###################### Starting [MyThread-5] <Task-5>
 * [MyThread-5] <Task-5> Tick Tick: 1
 * [MyThread-1] <Task-1> Tick Tick: 2
 * [MyThread-1] <Task-1> Tick Tick: 3
 * [MyThread-2] <Task-2> Tick Tick: 2
 * [MyThread-3] <Task-3> Tick Tick: 2
 * [MyThread-2] <Task-2> Tick Tick: 3
 * [MyThread-3] <Task-3> Tick Tick: 3
 * [MyThread-4] <Task-4> Tick Tick: 2
 * [MyThread-5] <Task-5> Tick Tick: 2
 * ###################### Ending [MyThread-1] <Task-1>
 * [MyThread-4] <Task-4> Tick Tick: 3
 * ###################### Ending [MyThread-2] <Task-2>
 * ###################### Ending [MyThread-3] <Task-3>
 * ###################### Ending [MyThread-4] <Task-4>
 * [MyThread-5] <Task-5> Tick Tick: 3
 * ###################### Ending [MyThread-5] <Task-5>
 */
public class NamingExecutorThreads {
    public static void main (String[] args) {
        System.out.println("******************* Starting Main ");

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

    public LoopTask() {
        this.nthTask = instanceCount++;
    }
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

        System.out.println("###################### Ending [" + Thread.currentThread().getName() + "] <Task-"+nthTask+">");

    }


}

class MyThreadFactory implements ThreadFactory {

    private static int instanceCount = 0;

    private int nthThread;

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "MyThread-" + (instanceCount++));
        return t;
    }

    public MyThreadFactory() {
        this.nthThread = instanceCount++;
    }
}