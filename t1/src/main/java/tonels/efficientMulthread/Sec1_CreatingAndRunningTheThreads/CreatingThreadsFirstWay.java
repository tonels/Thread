package tonels.efficientMulthread.Sec1_CreatingAndRunningTheThreads;

/**
 * Starting main!!!!
 * Ending main!!!
 * ThreadName2 Starting First Way -------
 * ThreadName3 Starting First Way -------
 * ThreadName1 Starting First Way -------
 */
public class CreatingThreadsFirstWay {
    public static void main(String[] args) {
        System.out.println("Starting main!!!!");
        new FirstWay();
        new FirstWay();
        new FirstWay();
        System.out.println("Ending main!!!");
    }
}


class FirstWay extends Thread {

    // static 变量 + 构造方法实现
    // 线程内共享变量，记录线程数量，每 New 一次，这里 + 1
    private static int count = 1;
    private int id;

    public FirstWay() {
        id = count++;
        this.start();
    }


    @Override
    public void run() {
        Thread.currentThread().setName("ThreadName" + id);
        System.out.println(Thread.currentThread().getName() + " Starting First Way -------");
    }

}
