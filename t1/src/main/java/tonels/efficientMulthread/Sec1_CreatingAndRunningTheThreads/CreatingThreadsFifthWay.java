package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

import java.util.concurrent.TimeUnit;

public class CreatingThreadsFifthWay {

    public static void main (String[] args) throws InterruptedException {
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

        // join()方法，可等当前线程执行完，才进行下一步
//        t.join();
        System.out.println("Ending Main........");
    }
}
