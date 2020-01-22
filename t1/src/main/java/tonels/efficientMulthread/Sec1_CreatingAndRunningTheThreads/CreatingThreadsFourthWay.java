package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Starting Main..........
 * Ending Main..........
 * Thread-2 Tick Tick: 1
 * Thread-1 Tick Tick: 1
 * Thread-1 Tick Tick: 2
 * Thread-2 Tick Tick: 2
 * Thread-2 Tick Tick: 3
 * Thread-1 Tick Tick: 3
 */
public class CreatingThreadsFourthWay {
    public static void main(String[] args) {
        System.out.println("Starting Main..........");

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(new FourthWay());
        executorService.submit(new FourthWay());

        executorService.shutdown();

        System.out.println("Ending Main..........");
    }
}

class FourthWay implements Runnable {

    // 用来监视线程数量
    static int count = 1;
    int id = 0;

    FourthWay() {
        this.id = count++;
    }

    public void run() {
        for (int i = 1; i <= 3; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread-" + id + " Tick Tick: " + i);
        }
    }


}
