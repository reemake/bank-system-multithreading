import entities.Bank;

public class Main {

    static int WORKERS_COUNT = 20;
    static int CLIENTS_PER_MINUTE = 50;
    static long CASHBOX_START_MONEY = 10000L;

    public static void main(String[] args) {
        Bank bank = new Bank(CLIENTS_PER_MINUTE, WORKERS_COUNT, CASHBOX_START_MONEY);
        try {
            bank.startWorking();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }

}
