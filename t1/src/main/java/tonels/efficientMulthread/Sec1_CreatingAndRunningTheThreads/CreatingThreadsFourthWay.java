package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

import java.util.concurrent.TimeUnit;

public class CreatingThreadsFourthWay {
    public static void main (String[] args) {
        System.out.println ("Starting Main..........");
        Thread t = new Thread(new FourthWay());
        t.start();
        new Thread(new FourthWay()).start();
        System.out.println ("Ending Main..........");
    }
}

class FourthWay implements Runnable {

    static int count = 1;
    int id = 0;

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

    FourthWay() {
        this.id = count++;
    }
}
