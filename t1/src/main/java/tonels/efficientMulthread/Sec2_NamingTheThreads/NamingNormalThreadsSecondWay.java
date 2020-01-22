package tonels.efficientMulthread.Sec2_NamingTheThreads;

import java.util.concurrent.TimeUnit;

/**
 * **************** Starting main Thread
 * **************** Ending main Thread
 * ################ [ThreadName <1>] Started task: <Task-1>
 * [ThreadName <1>] <Task-1> Tick Tick: 1
 * ################ [ThreadName <0>] Started task: <Task-0>
 * [ThreadName <0>] <Task-0> Tick Tick: 1
 * [ThreadName <1>] <Task-1> Tick Tick: 2
 * [ThreadName <1>] <Task-1> Tick Tick: 3
 * ################ [ThreadName <1>] Ending task: <Task-1>
 * [ThreadName <0>] <Task-0> Tick Tick: 2
 * [ThreadName <0>] <Task-0> Tick Tick: 3
 * ################ [ThreadName <0>] Ending task: <Task-0>
 */
public class NamingNormalThreadsSecondWay {

    public static void main(String[] args) {

        System.out.println("**************** Starting main Thread");

        new Thread(new SecondWay(), "MyThread-1").start();
        new Thread(new SecondWay(), "MyThread-2").start();

        Thread t3 = new Thread(new SecondWay(), "MyThread-3");

//        t3.start();
//
//        try {
//            TimeUnit.MILLISECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        t3.setName("RenamedThread-3");

        System.out.println("**************** Ending main Thread");
    }
}

class SecondWay implements Runnable {

    private static int instanceNumber = 0;

    private  int nthInstance ;
    public SecondWay() {
        this.nthInstance = instanceNumber++;
    }

    @Override
    public void run() {

        Thread.currentThread().setName("ThreadName <" + nthInstance + ">");
        System.out.println("################ [" + Thread.currentThread().getName() + "] Started task: <Task-" + nthInstance + ">");

        for (int i = 1; i <= 3; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] <Task-" + nthInstance + "> Tick Tick: " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("################ [" + Thread.currentThread().getName() + "] Ending task: <Task-" + nthInstance + ">");
    }


}
