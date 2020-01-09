package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

import java.util.concurrent.TimeUnit;

public class CreatingThreadsSecondWay {
    public static void main (String[] args) {
        System.out.println("Starting main!!!!");
        new SecondWay().start();
        Thread thread = new FirstWay();
        thread.start();
        System.out.println("Ending main!!!");
    }
}

class SecondWay extends Thread {

    private static int count = 1;
    private int id;

    @Override
    public void run() {
        System.out.println("Starting First Way -------");
        for(int i = 1; i <= 10; i++) {
            System.out.println("<"+ id + "> Count: " +i);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ending First Way -------");
    }

    public SecondWay() {
        id = count++;
    }
}

