package tonels.futureTask.f2;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main3 {

    public static int[] array = new Random().ints(100_000_000, 0, 9).toArray();
    public static int threadCount = 0;

    public static List<Future<Long>> findSum() {
        List<Future<Long>> result = new ArrayList<>();
        List<Callable<Long>> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadCount = i;
            threadList.add(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    System.out.println("thread begins to calculate sum");
                    int start = threadCount * 10_000_000;
                    int end = start + 10_000_000;
                    long result = 0;
                    for (int i = start; i < end; i++) {
                        result += array[i];
                    }
                    System.out.println("thread finished calculate sum");
                    return result;
                }
            });
        }
        ExecutorService service = Executors.newFixedThreadPool(12);
        try {
            result = service.invokeAll(threadList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
        return result;
    }

    public static void main(String[] args) {
        Long sum = 0L;
        Instant start = Instant.now();
        List<Future<Long>> result = findSum();
        for (Future<Long> itr : result) {
            try {
                sum += itr.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        Instant end = Instant.now();
        System.out.println("the sum is: " + sum + " , " + Duration.between(start, end).toMillis() + " miliseconds");

        start = Instant.now();
        int sum2 = Arrays.stream(array).sum();
        end = Instant.now();
        System.out.println("the sum is: " + sum2 + " , " + Duration.between(start, end).toMillis() + " miliseconds");
    }

}

