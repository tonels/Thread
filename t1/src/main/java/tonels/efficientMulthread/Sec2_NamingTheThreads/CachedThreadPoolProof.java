package tonels.efficientMulthread.Sec2_NamingTheThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class CachedThreadPoolProof {

    public static void main (String[] args) {

        System.out.println("################# Starting Main ");

        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactoryCorrect());

        executorService.execute(new LoopTask());
        executorService.execute(new LoopTask());


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.execute(new LoopTask());
        executorService.execute(new LoopTask());
        executorService.execute(new LoopTask());

        executorService.shutdown();
        System.out.println("******************* Ending Main ");
    }
}

class LoopTaskCachedThreadPool implements Runnable {

    private static int instanceCount = 0;
    private int nthTask;

    public LoopTaskCachedThreadPool() {
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

        System.out.println("********************** Ending [" + Thread.currentThread().getName() + "] <Task-"+nthTask+">");
    }


}

// 基于线程工厂，构建缓冲线程池
class ThreadFactoryCorrect implements ThreadFactory {

    private static int nthThread = 1;

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "MyThread-" + (nthThread++));
        return t;
    }
}