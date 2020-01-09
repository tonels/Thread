package tonels.bank;

public class WithdrawOperation extends Operation {
    public WithdrawOperation(Account account1, double amount) {
        super(account1, amount);
    }

    @Override
    public OperationResult call() throws Exception {
        OperationResult operationResult = new OperationResult(this);
        operationResult.setSuccess(getAccount1().withdraw(getAmount()));
        operationResult.setComplete(true);
        return operationResult;
    }
}
