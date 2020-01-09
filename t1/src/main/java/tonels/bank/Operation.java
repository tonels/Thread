package tonels.bank;

import lombok.Data;

import java.util.concurrent.Callable;

@Data
public abstract class Operation implements Callable<OperationResult> {
    private static long idCounter;
    private long id;
    private Account account1;
    private Account account2;
    private double amount;
    private long timeEnd;

    public Operation(Account account1, double amount) {
        super();
        this.id = idCounter++;
        this.account1 = account1;
        this.amount = amount;
        this.timeEnd = System.currentTimeMillis();
    }

    public Operation(Account account1, Account account2, double amount) {
        super();
        this.id = idCounter++;
        this.account1 = account1;
        this.account2 = account2;
        this.amount = amount;
        this.timeEnd = System.currentTimeMillis();
    }



}