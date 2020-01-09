package tonels.callBack_runnable.c1;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialCalculator implements Callable<Integer> {

    private int number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        if (number != 0) {
            for (int i = 1; i <= number; i++) {
                result += i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.println("结果是 ：" + number + " = " + result);
        return result;
    }
}