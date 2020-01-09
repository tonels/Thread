package tonels.efficientMulthread.Sec3_ReturningValuesFromThreads;

import java.util.concurrent.TimeUnit;

public class NormalThreadSecondWay {
    public static void main (String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "] Starting ##########################");

        SumTask sumTask1 = new SumTask(2,3,500);
        sumTask1.register(new ObserverImpl("Task-1"));
        SumTask sumTask2 = new SumTask(3,4,1000);
        sumTask2.register(new ObserverImpl("Task-2"));
        SumTask sumTask3 = new SumTask(4,5,500);
        sumTask3.register(new ObserverImpl("Task-3"));

        new Thread(sumTask1, "Thread-1").start();
        new Thread(sumTask2, "Thread-2").start();
        new Thread(sumTask3, "Thread-3").start();

        System.out.println("[" + Thread.currentThread().getName() + "] Ending ****************************");
    }
}

interface Observer<T> {
    public void update(T sum);
}

class ObserverImpl implements Observer<Integer> {

    String task;

    public ObserverImpl(String threadName) {
        this.task = threadName;
    }

    public void update(Integer sum) {
        System.out.println("<" + task +  "> has published/updated the sum to be " + sum);
    }
}

//This is the subject class. We would need to implements 3 methods: to register observer, to unregister observer and to notify observer. It will be through notify observer that we will call the update method of the observer. Also, as a rule to the observer interface, the subject does not have any idea about the observer instances which are subscribed. It just has the observer interface value.
class SumTask implements Runnable{

    private static int instanceNumber = 0;
    private int nthTask;
    private String taskId;
    private long sleepDuration;
    private Observer<Integer> observer;
    private int a, b;

    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Starting ################");
        try {
            TimeUnit.MILLISECONDS.sleep(sleepDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notifyToObserver(a + b);

        System.out.println("[" + Thread.currentThread().getName() + "] Ending ******************");
    }

    public void register(Observer observer) {
        this.observer = observer;
    }

    public void notifyToObserver (int sum) {
        observer.update(sum);
    }

    public SumTask(int a, int b, long sleepDuration) {
        this.a = a;
        this.b = b;
        this.sleepDuration = sleepDuration;
        this.nthTask = ++instanceNumber;
        this.taskId = "Task-" + nthTask;
    }
}
