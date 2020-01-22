package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

import java.util.concurrent.TimeUnit;

/**
 * Starting main!!!!
 * Ending main!!!
 * TN-1, Count: 1
 * TN-2, Count: 1
 * TN-1, Count: 2
 * TN-2, Count: 2
 * TN-1, Count: 3
 * TN-2, Count: 3
 * TN-2 end...
 * TN-1 end...
 */
public class CreatingThreadsSecondWay {
    public static void main(String[] args) {
        System.out.println("Starting main !!!");

        new SecondWay().start();
        new SecondWay().start();

        System.out.println("Ending main !!!");
    }
}

class SecondWay extends Thread {

    private static int count = 1;
    private int id;

    public SecondWay() {
        id = count++;
    }


    @Override
    public void run() {
        Thread.currentThread().setName("TN-" + id);
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName() + ", Count: " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " end...");
    }

}

