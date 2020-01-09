package tonels.futureTask.f1;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class FutureCallable implements Callable<HashMap<String, String>> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public HashMap<String, String> call() throws Exception {

        System.out.println("异步任务开始执行....");
        Thread.sleep(2000);
        System.out.println("异步任务执行完毕，返回执行结果!!!!");

        // 构造代码块在创建对象时被调用，每次创建对象都会被调用，并且构造代码块的执行次序优先于类构造函数。
        return new HashMap<String, String>(){
            {
                this.put("futureKey", "成功获取future异步任务结果");
            }
        };
    }
}
