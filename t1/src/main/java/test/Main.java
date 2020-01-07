package test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        delay5sec();
        execEverySec();
        secBetweenExec();
        fixedThreadPool();
        cashedThreadPool();
    }

    //использует два потока
    private void fixedThreadPool() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            service.submit(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

        //закрытие пула после завершения работы всех его потоков
        service.shutdown();
        try {
            System.out.println("Ready? " + service.awaitTermination(1, TimeUnit.MINUTES));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //принудительное завершение работы
        /*List<Runnable> rejected = service.shutdownNow(); //невыполненные задачи
        System.out.println("Rejected: "+rejected.size());
        try {
            System.out.println("Ready? " + service.awaitTermination(1, TimeUnit.MINUTES));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    //сам определяет кол-во потоков и кому какая работа выпадет
    private void cashedThreadPool() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.submit(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
    }

    //выполнит один раз через 5 сек после вызова ф-и
    private void delay5sec() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(new Runnable() {
            public void run() {
                Thread.currentThread().setName("Delayed");
                System.out.println(Thread.currentThread().getName());
            }
        }, 5, TimeUnit.SECONDS);
    }

    //запускает поток через каждые 3 сек
    private void execEverySec() {
        int[] arr = new Random().ints(100000, 0, 100000).toArray();
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {
            public void run() {
                //имя по умолчанию: pool-N-thread-M
                System.out.println("I do it every sec " + Thread.currentThread().getName());
                printSortingTime(arr);
            }
        }, 0, 3, TimeUnit.SECONDS);
    }

    //запускает поток через 3 сек после окончания работы
    private void secBetweenExec() {
        int[] arr = new Random().ints(100000, 0, 100000).toArray();
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("I wait a sec after previous end " + Thread.currentThread().getName());
                printSortingTime(arr);
            }
        }, 0, 3, TimeUnit.SECONDS);
    }

    private void printSortingTime(int[] arr) {
        long start = System.currentTimeMillis();
        selectionSort(arr);
        long finish = System.currentTimeMillis();
        double sortingTime = (double) (finish - start) / 1000;
        System.out.println("Sorting time of arr[" + arr.length + "]: " + sortingTime + " seconds");
        System.out.println("=================================");
    }

    private void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int indexMin = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[indexMin]) indexMin = j;
            }
            if (indexMin != i) {
                int t = arr[i];
                arr[i] = arr[indexMin];
                arr[indexMin] = t;
            }
        }
    }

}
