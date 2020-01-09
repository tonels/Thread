package tonels.futureTask.f1;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("====进入主线程执行任务");

        // 通过线程池管理多线程
        ExecutorService threadPool = Executors.newCachedThreadPool();

        //线程池提交一个异步任务
        System.out.println("====提交异步任务");
        FutureCallable futureCallable = new FutureCallable();
        Future<HashMap<String, String>> future = threadPool.submit(futureCallable);

        System.out.println("====提交异步任务之后，立马返回到主线程继续往下执行");
        Thread.sleep(1000);

        System.out.println("====此时需要获取上面异步任务的执行结果");

        boolean flag = true;
        while (flag) {
            // 异步任务完成并且未被取消，则获取返回的结果
            if (future.isDone() && !future.isCancelled()) {
                HashMap<String, String> futureResult = future.get();
                System.out.println("====异步任务返回的结果是：" + futureResult.get("futureKey"));
                flag = false;
            }
        }

        // 关闭线程池
        if (!threadPool.isShutdown()) {
            threadPool.shutdown();
        }
    }

}