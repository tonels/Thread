package tonels.futureTask.f2;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

public class Main2 {

    public static int[] array;
    public static int[] array2 = new int[100_100_100];
    public static int currentThread = 0;
    public static List<Future<Long>> sum;
    public static int count = 0;

    public static void generateUsingThread(int threadNum, int count) {
        array = new int[count];
        Thread[] threadArray = new Thread[threadNum];
        for (int i = 1; i <= threadNum; i++) {
            System.out.println("creating thread #" + i + " to generating array");
            threadArray[i - 1] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int num = currentThread;
                    int start = (currentThread) * (50_000_000);
                    currentThread++;
                    int finish = start + 50_000_000;
                    for (int j = start; j < finish; j++) {
                        array[j] = (int) (Math.random() * 10);
                    }
                    System.out.println("Thread" + " " + (num + 1) + " is done generating!");
                }
            });
            threadArray[i - 1].start();
        }
        for (int i = 0; i < threadNum; i++)
            try {
                threadArray[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    public static void generateUsingStream(int count) {
        array2 = new Random().ints(count, 0, 9).toArray();
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        Main2.generateUsingThread(2, 100_000_000);
        Instant end = Instant.now();
        System.out.println("finished generating, thread method takes: " + Duration.between(start, end).toMillis() + " mili-seconds");
        start = Instant.now();
        Main2.generateUsingStream(100_000_000);
        end = Instant.now();
        System.out.println("finished generating, thread method takes: " + Duration.between(start, end).toMillis() + " mili-seconds");
    }

}

