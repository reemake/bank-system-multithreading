package entities;

import exceptions.NotEnoughMoneyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BankWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BankWorker.class);

    private int id;
    private BlockingQueue<Client> clientQueue;
    private Queue<Client> allClientQueue;
    private Cashbox cashbox;

    public BankWorker(int id, Queue<Client> allClientQueue, Cashbox cashbox) {
        this.id = id;
        this.clientQueue = new ArrayBlockingQueue<>(20, true);
        this.allClientQueue = allClientQueue;
        this.cashbox = cashbox;
    }

    private Client getClient() throws InterruptedException {
        logger.info("[{}] Ожидает нового клиента...", this.id);
        synchronized (allClientQueue) {
            while (allClientQueue.isEmpty()) {
                allClientQueue.wait();
            }
            this.clientQueue.add(allClientQueue.remove());
            return this.clientQueue.take();
        }
    }

    @Override
    public void run() {
        for (;;) {
            try {
                Client currentClient = getClient();
                logger.info("[{}] Начинает обслуживать клиента #{}", this.id, currentClient.getId().toString());
                Thread.sleep(currentClient.getOperationDuration());
                long clientRequest = currentClient.getOperationValue();
                switch (currentClient.getOperationType()) {
                    case GET_MONEY:
                        try {
                            this.cashbox.withdrawMoney(clientRequest);
                            logger.info("Клиент снял {}$ [CASHBOX = {}$]", clientRequest, this.cashbox.getMoney());
                        } catch (NotEnoughMoneyException e) {
                            logger.info("В кассе недостаточно денег ({}$) для клиента [CASHBOX = {}$]", clientRequest, this.cashbox.getMoney());
                        }
                        break;
                    case PUT_MONEY:
                        this.cashbox.putMoney(clientRequest);
                        logger.info("Клиент положил {}$ [CASHBOX = {}$]", clientRequest, this.cashbox.getMoney());
                        break;
                    default:
                        logger.error("[{}] Неизвестная операция - {}", this.id, currentClient.getOperationType().toString());
                        break;
                }
            } catch (InterruptedException e) {
                logger.error(e.toString());
            }
        }

    }

}
