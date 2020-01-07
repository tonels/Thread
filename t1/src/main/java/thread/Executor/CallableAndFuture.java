package thread.Executor;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * Callback 使用场景
 * 执行完线程后返回结果
 */
public class CallableAndFuture {

    // -------------- -----回调函数简单使用---------------------------------------
    @Test
    public void t1() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            public String call() throws Exception {
                return "MOBIN";
            }
        });
        System.out.println("任务的执行结果：" + future.get());
    }

    // -------------- -----回调函数简单使用---------------------------------------

    /**
     * 回调函数使用场景
     * 当多个线程任务执行时，返回放到 List<Future> 中，如果有一个失败是，其他也失败的处理
     */
    // -------------- --- 回调函数使用场景模拟---------------------------------------
//    @Test
//    public void t2() {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        List<Future<String>> resultList = new ArrayList<Future<String>>();
//
//        // 创建10个任务并执行
//        for (int i = 0; i < 10; i++) {
//            // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
//            Future<String> future = executorService.submit(new TaskWithResult(i));
//            // 将任务执行结果存储到List中
//            resultList.add(future);
//        }
//        executorService.shutdown();
//
//        // 遍历任务的结果
//        for (Future<String> fs : resultList) {
//            try {
//                System.out.println(fs.get()); // 打印各个线程（任务）执行的结果
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                executorService.shutdownNow();
//                e.printStackTrace();
//                return;
//            }
//        }
//    }
//
//
//    class TaskWithResult implements Callable<String> {
//        private int id;
//
//        public TaskWithResult(int id) {
//            this.id = id;
//        }
//
//        /**
//         * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。
//         *
//         * @return
//         * @throws Exception
//         */
//        public String call() throws Exception {
//            System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());
//            if (new Random().nextBoolean())
//                throw new TaskException("Meet error in task." + Thread.currentThread().getName());
//            // 一个模拟耗时的操作
//            for (int i = 999999999; i > 0; i--) ;
//            return "call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();
//        }
//    }
//
//    class TaskException extends Exception {
//        public TaskException(String message) {
//            super(message);
//        }
//
//
//    }

}
