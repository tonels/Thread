package tonels.threadPool.schedule;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class S1 {

    @Test
    public void tt() {
        // todo 这里如果用 Test测试，执行会有问题，
        // 问题待发掘
    }

    /**
     * 创建延时任务
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("main start...");
        // 创建任务
        Runnable task1 = () -> {
            System.out.println("任务运行 : " + new Date());
        };

        // 创建任务
        Runnable task2 = () -> {
            System.out.println("任务运行 : " + new Date());
        };

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        System.out.println("5 秒后，延时任务启动..." + new Date());
        scheduledExecutorService.schedule(task1, 5, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(task2, 5, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
        System.out.println("Main finished...");

    }
}


