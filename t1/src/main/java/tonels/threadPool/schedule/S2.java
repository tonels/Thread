package tonels.threadPool.schedule;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class S2 {

    @Test
    public void tt() {
        // todo 这里如果用 Test测试，执行会有问题，
        // 问题待发掘
    }

    /**
     * 定时执行任务
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Thread main started");
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        // Create a task
        Runnable task1 = () -> {
            System.out.println("Executing the task1 at: " + new Date());
        };

        scheduledExecutorService.scheduleAtFixedRate(task1, 0, 2, TimeUnit.SECONDS);

        System.out.println("Thread main finished");
    }

}


