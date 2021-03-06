package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

import java.util.concurrent.TimeUnit;

/**
 * Starting main.......
 * Ending main.......
 * TN-2> Tick Tick: 1
 * TN-1> Tick Tick: 1
 * TN-1> Tick Tick: 2
 * TN-2> Tick Tick: 2
 * TN-1> Tick Tick: 3
 * TN-2> Tick Tick: 3
 */
public class CreatingThreadsThirdWay {
    public static void main(String[] args) {

        System.out.println("Starting main.......");
        new ThirdWay();
        new ThirdWay();
        System.out.println("Ending main.......");
    }
}

class ThirdWay implements Runnable {

    static int count = 1;
    int id = 0;

    ThirdWay() {
        this.id = count++;
        new Thread(this).start();
    }

    @Override
    public void run() {
        Thread.currentThread().setName("TN-" + id);
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName() + "> Tick Tick: " + i);
        }
    }


}