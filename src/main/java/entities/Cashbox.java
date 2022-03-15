package entities;

import exceptions.NotEnoughMoneyException;

/**
 * Class which is describing Cashbox entity
 * @see Bank
 * @see Client
 * @see BankWorker
 */
public class Cashbox {

    /** Amount of money on cashbox */
    private volatile long money;

    /** Constructor with parameters */
    public Cashbox(long money) {
        this.money = money;
    }

    synchronized public long getMoney() {
        return money;
    }

    synchronized public void setMoney(long money) {
        this.money = money;
    }

    /**
     * Method for put money to cashbox
     */
    synchronized public void putMoney(long howMuchToPut) {
        this.money += howMuchToPut;
    }

    /**
     * Method for withdraw money from cashbox
     * @param howMuchToWithdraw amount of money to withdraw
     * @throws NotEnoughMoneyException
     */
    synchronized public void withdrawMoney(long howMuchToWithdraw) throws NotEnoughMoneyException {
        if (money - howMuchToWithdraw < 0)
            throw new NotEnoughMoneyException();
        else
            this.money -= howMuchToWithdraw;
    }

}
