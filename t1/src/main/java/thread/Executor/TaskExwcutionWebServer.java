package thread.Executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 */
public class TaskExwcutionWebServer {

    private static final int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) {
        while (true){
            Runnable task = new Runnable() {
                public void run() {
                    System.out.println("llll");
                }
            };
            exec.execute(task);
        }
    }
}
