package tonels.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Bank {

    private ExecutorService service;
    private Thread clientThread;
    private Runnable resultRun;
    private volatile boolean isWorking;
    private List<Future<OperationResult>> results;

    public Bank() {
        isWorking = false;
        results = new CopyOnWriteArrayList<>();
        service = Executors.newFixedThreadPool(5);
        resultRun = () -> {
            List<OperationResult> completeOperations = new ArrayList<>();
            results.stream().filter((result) -> {
                return result.isDone();
            });

//			Iterator <Future<OperationResult>> iterator = results.iterator();			while (iterator.hasNext()) {
//				Future<OperationResult> future = iterator.next();
//				if (future.isDone()) {
//					try {
//						completeOperations.add(future.get());
//					} catch (InterruptedException | ExecutionException e) {
//						// TODO Please implement full bank exception
//					}
//					iterator.remove();
//				}
//			}

        };

        clientThread = new Thread(() -> {
            while (isWorking) {
                try {
                    Thread.sleep((10 + (long) (Math.random() * 3700)));
                } catch (InterruptedException e) {
                }
                Operation operation;
                double rnd = Math.random();
                if (rnd < 1 / 3) {
                    operation = new DepositOperation(getRandomAccount(), Math.random() * 40 + 10);
                } else if (rnd < 2 / 3) {
                    operation = new WithdrawOperation(getRandomAccount(), Math.random() * 40 + 10);
                } else {
                    operation = new TransferOperation(getRandomAccount(), getRandomAccount(), Math.random() * 40 + 10);
                }
                Future<OperationResult> future = service.submit(operation);
            }
        }, "clientThread");
    }


    private Account getRandomAccount() {
        Account randomAccount = Repository.getInstance().getAccounts()
                .get((int) (Math.random() * Repository.getInstance().getAccounts().size()));
        return randomAccount;
    }

    public void start() {
        isWorking = true;
        clientThread.start();
    }

    public void stop() {
        isWorking = false;
    }

}
