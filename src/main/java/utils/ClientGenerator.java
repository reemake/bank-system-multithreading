package utils;

import entities.Client;
import entities.EClientOperation;

import java.util.ArrayList;
import java.util.List;

public class ClientGenerator {

    static final long MIN_OPERATION_VALUE = 100L;
    static final long MAX_OPERATION_VALUE = 5000L;
    static final long MIN_OPERATION_DURATION = 1000L;
    static final long MAX_OPERATION_DURATION = 10000L;

    public static List<Client> generateClients(int n) {
        List<Client> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            EClientOperation operationType = Math.random() >= 0.5 ? EClientOperation.GET_MONEY : EClientOperation.PUT_MONEY;
            long value = MIN_OPERATION_VALUE + (long) (Math.random() * (MAX_OPERATION_VALUE - MIN_OPERATION_VALUE));
            long duration = MIN_OPERATION_DURATION + (long) (Math.random() * (MAX_OPERATION_DURATION - MIN_OPERATION_DURATION));
            result.add(new Client(operationType, value, duration));
        }
        return result;
    }
}
