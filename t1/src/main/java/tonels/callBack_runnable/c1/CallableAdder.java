package tonels.callBack_runnable.c1;

import java.util.concurrent.Callable;

public class CallableAdder implements Callable<Integer> {
    Integer operand1;
    Integer operand2;

    CallableAdder(Integer operand1, Integer operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public Integer call() throws Exception {
        // TODO Auto-generated method stub
        System.out.println(Thread.currentThread().getName() + " says : partial Sum for " + operand1 + " and " + operand2 + " is " + (operand1 + operand2));
        return operand1 + operand2;
    }
}
