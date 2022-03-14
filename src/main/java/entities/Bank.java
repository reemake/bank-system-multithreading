package entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ClientGenerator;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Bank {

    final static int SERVICE_TIME = 10;
    private final Logger logger = LoggerFactory.getLogger(Bank.class);

    private int clientsPerMinute;
    private Thread[] workers;
    private Cashbox cashbox;
    private Queue<Client> clients;

    public Bank(int clientsPerMinute, int workersCount, long cashboxCapacity) {
        this.clientsPerMinute = clientsPerMinute;
        this.workers = new Thread[workersCount];
        this.cashbox = new Cashbox(cashboxCapacity);
        this.clients = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < workersCount; i++) {
            this.workers[i] = new Thread(new BankWorker(i, this.clients, this.cashbox));
            this.workers[i].start();
        }
    }

    public void startWorking() throws InterruptedException {
        synchronized (this.clients) {
            int counter = 0;
            while (counter < SERVICE_TIME) {
                logger.info("За работу!");
                List<Client> newClients = ClientGenerator.generateClients(clientsPerMinute);
                this.clients.addAll(newClients);
                for (Client client : newClients) {
                    logger.info("Клиент {} добавлен в очередь", client.getId());
                }
                this.clients.wait(60000L, 0);
                this.clients.notifyAll();
                counter++;
            }
        }
    }
}
