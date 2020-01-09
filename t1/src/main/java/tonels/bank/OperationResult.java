package tonels.bank;

public class OperationResult {
    private Operation operation;
    private boolean isComplete;
    private boolean isSuccess;


    public OperationResult(Operation operation) {
        super();
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }


}