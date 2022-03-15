package entities;

import java.util.UUID;

/**
 * Class which is describing Client entity
 * @see Bank
 * @see Cashbox
 * @see BankWorker
 * @see utils.ClientGenerator
 * @see EClientOperation
 */
public class Client {

    /** Personal id */
    private UUID id;

    /** Type of operation with the money (Put/Get) */
    private EClientOperation operationType;

    /** Amount of money (generating randomly) */
    private long operationValue;

    /** Service duration (generating randomly) */
    private long operationDuration;

    /** Constructor with parameters */
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
