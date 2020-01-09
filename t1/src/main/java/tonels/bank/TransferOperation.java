package tonels.bank;

public class TransferOperation extends Operation {

    public TransferOperation(Account account1, Account account2, double amount) {
        super(account1, account2, amount);

    }

    @Override
    public OperationResult call() throws Exception {
        OperationResult operationResult = new OperationResult(this);
        operationResult.setSuccess(getAccount1().transfer(getAmount(), getAccount2()));
        operationResult.setComplete(true);
        return operationResult;
    }

}
