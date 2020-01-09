package tonels.callBack_runnable.c2;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class CallTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        submitCallablesWithExecutor();
//        submitCollectionOfCallablesWithExecutor();
//        executeRunnablesWithExecutor();
//        submitRunnablesWithExecutor();
//        singleThreadedProcessing();
//        multiThreadedProcessing();
    }


    public static void submitCallablesWithExecutor() throws InterruptedException, ExecutionException, TimeoutException {

        Instant start = Instant.now();

        new ExecutorServiceCallableSample().submitCallablesWithExecutor();

        System.out.println(String.format("submitCallablesWithExecutor took %s ms",
                ChronoUnit.MILLIS.between(start, Instant.now())));
    }


    public static void submitCollectionOfCallablesWithExecutor() throws InterruptedException, ExecutionException, TimeoutException {

        Instant start = Instant.now();

        new ExecutorServiceCallableSample().submitMultipleCallablesWithExecutor();

        System.out.println(String.format("submitMultipleCallablesWithExecutor took %s ms",
                ChronoUnit.MILLIS.between(start, Instant.now())));
    }


    public static void executeRunnablesWithExecutor() throws InterruptedException {

        Instant start = Instant.now();

        new ExecutorServiceRunnableSample().executeRunnablesWithExecutor();

        System.out.println(String.format("executeRunnablesWithExecutor took %s ms",
                ChronoUnit.MILLIS.between(start, Instant.now())));
    }


    public static void submitRunnablesWithExecutor() throws InterruptedException, ExecutionException, TimeoutException {

        Instant start = Instant.now();

        new ExecutorServiceRunnableSample().submitRunnablesWithExecutor();

        System.out.println(String.format("submitRunnablesWithExecutor took %s ms",
                ChronoUnit.MILLIS.between(start, Instant.now())));
    }


    public static void singleThreadedProcessing() {

        SimpleThreadSample threadExample = new SimpleThreadSample();

        LocalDateTime start = LocalDateTime.now();

        threadExample.doSingleThreadedWork();

        System.out.println(String.format("doSingleThreadedWork took %s ms",
                ChronoUnit.MILLIS.between(start, LocalDateTime.now())));
    }


    public static void multiThreadedProcessing() throws InterruptedException {

        SimpleThreadSample threadExample = new SimpleThreadSample();

        LocalDateTime start = LocalDateTime.now();

        threadExample.doMultiThreadedWork();

        System.out.println(String.format("doMultiThreadedWork took %s ms",
                ChronoUnit.MILLIS.between(start, LocalDateTime.now())));
    }


}
