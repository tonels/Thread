package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

import java.util.concurrent.TimeUnit;

public class CreatingThreadsThirdWay {
    public static void main (String[] args) {
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

    public void run() {
        for(int i = 1 ; i <= 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("<"+id+"> Tick Tick: " + i);
        }
    }



}