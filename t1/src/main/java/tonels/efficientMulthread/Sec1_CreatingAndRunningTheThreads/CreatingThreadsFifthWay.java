package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

import java.util.concurrent.TimeUnit;

public class CreatingThreadsFifthWay {
    public static void main (String[] args) {
        System.out.println("Starting Main........");
        Thread t = new Thread(new Runnable() {
            public void run() {
                for(int i = 1 ; i <= 10; i++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Tick Tick: " + i);
                }
            }
        });
        t.start();
        System.out.println("Ending Main........");
    }
}
