package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

/**
 * todo
 * 如何正确给线程命名
 */
public class SecondWay_2 {
    public static void main(String[] args) {

        System.out.println("Starting main.......");
        new SeWay("TN-");
        new SeWay("TN-");
        System.out.println("Ending main.......");
    }


}

class SeWay implements Runnable {

    static int count = 1;
    int id = 0;
    String name;

    SeWay(String name) {
        this.id = count++;
        this.name = name + id;
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
