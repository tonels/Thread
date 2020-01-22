package tonels.efficientMulthread.Sec2_NamingTheThreads;

import java.util.concurrent.TimeUnit;

public class NamingNormalThreadsSecondWay {

    public static void main (String[] args) {

        System.out.println("############### Starting main Thread");

        // 给线程命名

        new Thread(new SecondWay(), "MyThread-1").start();
        new Thread(new SecondWay(), "MyThread-2").start();

        Thread t3 = new Thread(new SecondWay(), "MyThread-3");

        t3.start();

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t3.setName("RenamedThread-3");

        System.out.println("**************** Ending main Thread");
    }
}

class SecondWay implements Runnable {

    private static int instanceNumber = 0;
    private int nthInstance;

    public SecondWay() {
        this.nthInstance = instanceNumber++;
    }

    public void run() {

        System.out.println("################ [" + Thread.currentThread().getName() + "] Started task: <Task-" + nthInstance + ">");

        for(int i = 1; i <= 3; i++) {
            System.out.println("[" + Thread.currentThread().getName()+"] <Task-" + nthInstance + "> Tick Tick: " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("**************** [" + Thread.currentThread().getName() + "] Ending task: <Task-" + nthInstance + ">");
    }


}
