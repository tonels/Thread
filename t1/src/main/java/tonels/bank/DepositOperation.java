package tonels.bank;

public class DepositOperation extends Operation {

    public DepositOperation(Account account1, double amount) {
        super(account1, amount);
    }

    @Override
    public OperationResult call() throws Exception {
        OperationResult operationResult = new OperationResult(this);
        operationResult.setSuccess(getAccount1().deposit(getAmount()));
        operationResult.setComplete(true);
        return operationResult;
    }
}