package tonels.futureTask.f2;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static int[] array = new int[100_000_000];
    public static int currentThread = 0;
    public static List<Future<Long>> sum;

    public static void main(String[] args) {

        sum = new ArrayList<>();

        ExecutorService generateNumbers = Executors.newFixedThreadPool(10);
        System.out.println("创建线程池填充数组");

        for (int i = 1; i <= 5; i++) {
            System.out.println("creating thread #" + i + " to fill out the array");
            generateNumbers.execute(new Runnable() {
                @Override
                public void run() {
                    int num = currentThread;
                    int start = (currentThread) * (20_000_000);
                    int finish = start + 20_000_000;
                    currentThread++;
                    for (int j = start; j < finish; j++) {
                        array[j] = (int) (Math.random() * 10);
                    }
                    System.out.println("Thread" + " " + (num + 1) + " is done generating!");
                }
            });
        }
        generateNumbers.shutdown();
        try {
            generateNumbers.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            generateNumbers.shutdownNow();
        }

        System.out.println("创建线程池计算数组");
        ExecutorService addNum = Executors.newFixedThreadPool(10);
        currentThread = 0;
        Instant start = Instant.now();
        for (int i = 1; i <= 5; i++) {
            System.out.println("creating thread #" + i + " to calculate sum of the array!");
            sum.add(addNum.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    long result = 0L;
                    int start = (currentThread) * (20_000_000);
                    int finish = start + 20_000_000;
                    currentThread++;
                    for (int j = start; j < finish; j++) {
                        result += array[j];
                    }
                    return result;
                }
            }));
        }
        addNum.shutdown();
        long result = 0;
        for (Future<Long> temp : sum) {
            try {
                result += temp.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        Instant end = Instant.now();
        System.out.println("the sum of the array is: " + result + "\n and takes " + Duration.between(start, end).toMillis() + " mili- seconds");

        System.out.println("using stream operations to find the sum of the array: ");
        start = Instant.now();
        long streamResult = Arrays.stream(array).sum();
        end = Instant.now();
        System.out.println("the sum is: " + streamResult + "\n and takes " + Duration.between(start, end).toMillis() + " mili- seconds");
    }

}
