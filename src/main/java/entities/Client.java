package entities;

import java.util.UUID;

public class Client {

    private UUID id;
    private EClientOperation operationType;
    private long operationValue;
    private long operationDuration;

    public Client(EClientOperation operationType, long operationValue, long operationDuration) {
        this.id = UUID.randomUUID();
        this.operationType = operationType;
        this.operationValue = operationValue;
        this.operationDuration = operationDuration;
    }

    synchronized public UUID getId() {
        return id;
    }

    synchronized public EClientOperation getOperationType() {
        return operationType;
    }

    synchronized public long getOperationValue() {
        return operationValue;
    }

    synchronized public long getOperationDuration() {
        return operationDuration;
    }
}
